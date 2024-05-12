package com.example.shoppingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shoppingapp.adapter.IndicatorAdapter;
import com.example.shoppingapp.adapter.ProductImageViewPagerAdapter;
import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.base.RequestCallback;
import com.example.shoppingapp.databinding.ActivityProductDetailsBinding;
import com.example.shoppingapp.fragments.OptionDialog;
import com.example.shoppingapp.model.CartRequest;
import com.example.shoppingapp.model.ColorOption;
import com.example.shoppingapp.model.Option;
import com.example.shoppingapp.model.Product;
import com.example.shoppingapp.model.ProductImage;
import com.example.shoppingapp.model.SizeOption;

import java.util.ArrayList;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Response;

public class ProductDetailsActivity extends BaseActivity<ActivityProductDetailsBinding> {

    private Product product;
    private ProductImageViewPagerAdapter viewPagerAdapter;
    private boolean isColorSelected = false;
    private boolean isSizeSelected = false;
    private ColorOption selectColorOption;
    private SizeOption selectSizeOption;
    private int quantity = 1;
    private ArrayList<SizeOption> filterSizeOptionArrayList = new ArrayList<>();

    private IndicatorAdapter indicatorAdapter;
    private ArrayList<ProductImage> productImageArrayList = new ArrayList<>();

    @Override
    protected ActivityProductDetailsBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityProductDetailsBinding.inflate(inflater);
    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        product = (Product) getIntent().getSerializableExtra("product");
        if (product != null) {
            setTitle(product.getTitle());
            loadProductDetails();
        } else {
            finish();
        }

    }

    private void buildProductData() {
        productImageArrayList.addAll(product.getImages());
        viewPagerAdapter = new ProductImageViewPagerAdapter(ProductDetailsActivity.this, productImageArrayList);
        binding.productImageViewPager.setAdapter(viewPagerAdapter);
        indicatorAdapter = new IndicatorAdapter(productImageArrayList.size());
        binding.pagerIndicatorView.setLayoutManager(new LinearLayoutManager(ProductDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
        binding.pagerIndicatorView.setAdapter(indicatorAdapter);
        binding.productImageViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                indicatorAdapter.setSelectedDotPosition(position);
                indicatorAdapter.notifyDataSetChanged();
            }
        });


        binding.productBrand.setText(product.getBrand());
        binding.productName.setText(product.getTitle());
        binding.productPriceCurrent.setText(product.getPriceCurrent());
        binding.productPriceOriginal.setText(product.getPriceOriginal());
        binding.productDetails.setText(product.getDescription());

        binding.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectColorOption == null) {
                    showToast("Please select color!");
                    return;
                }

                if (selectSizeOption == null) {
                    showToast("Please select size!");
                    return;
                }
                Option selectOption = null;
                for (Option option : product.getOptions()) {
                    if (option.getColorOption().getId() == selectColorOption.getId() && option.getSizeOption().getId() == selectSizeOption.getId()) {
                        selectOption = option;
                    }
                }

                if (selectOption == null)
                    return;

                addProductToCart(new CartRequest(product.getId(), selectOption.getId(), quantity));


            }
        });

        binding.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity == 1)
                    return;

                quantity--;
                binding.tvQuantity.setText(String.valueOf(quantity));
            }
        });
        binding.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity == 10)
                    return;

                quantity++;
                binding.tvQuantity.setText(String.valueOf(quantity));
            }
        });

        binding.tvColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionDialog optionDialog = new OptionDialog();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                optionDialog.setColorOptionArrayList(product.getColorOptions());
                optionDialog.setSelectColorOption(selectColorOption);

                optionDialog.setOptionItemListener(new OptionDialog.OptionItemListener() {
                    @Override
                    public void onColorItemSelected(ColorOption colorOption) {
                        selectColorOption = colorOption;
                        isColorSelected = true;
                        filterSizeOptionArrayList.clear();

                        binding.ivColor.setVisibility(View.INVISIBLE);
                        binding.tvSelectColor.setVisibility(View.VISIBLE);
                        binding.tvSelectColor.setText(colorOption.getTitle());

                        product.getOptions().forEach(new Consumer<Option>() {
                            @Override
                            public void accept(Option option) {
                                if (option.getColorOption().equals(colorOption)) {
                                    filterSizeOptionArrayList.add(option.getSizeOption());
                                }
                            }
                        });
                    }

                    @Override
                    public void onSizeItemSelected(SizeOption sizeOption) {

                    }
                });
                optionDialog.show(ft, "dialog_color");
            }
        });

        binding.tvSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isColorSelected) {
                    OptionDialog sizeDialog = new OptionDialog();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.addToBackStack(null);
                    sizeDialog.setSizeOptionArrayList(filterSizeOptionArrayList);
                    sizeDialog.setSelectSizeOption(selectSizeOption);
                    sizeDialog.setOptionItemListener(new OptionDialog.OptionItemListener() {
                        @Override
                        public void onColorItemSelected(ColorOption colorOption) {

                        }

                        @Override
                        public void onSizeItemSelected(SizeOption sizeOption) {
                            selectSizeOption = sizeOption;
                            isSizeSelected = true;
                            binding.ivSize.setVisibility(View.INVISIBLE);
                            binding.tvSelectSize.setVisibility(View.VISIBLE);
                            binding.tvSelectSize.setText(sizeOption.getTitle());
                        }
                    });
                    sizeDialog.show(ft, "dialog_size");
                } else {
                    Toast.makeText(ProductDetailsActivity.this, "Please select color first", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void addProductToCart(CartRequest cartRequest) {

        Call<CartRequest> call = mainApi.addProductToCart(cartRequest);
        call.enqueue(new RequestCallback<CartRequest>() {
            @Override
            protected void onResponseSuccess(Call<CartRequest> call, Response<CartRequest> response) {
                showToast("Product is successfully added to cart!");
            }

            @Override
            protected void onResponseFailed(Call<CartRequest> call, Throwable t) {
                showToast(t.getMessage());
            }
        });


    }

    private void loadProductDetails() {
        Call<Product> call = mainApi.getProductDetails(product.getId());

        call.enqueue(new RequestCallback<Product>() {
            @Override
            protected void onResponseSuccess(Call<Product> call, Response<Product> response) {
                product = response.body();
                buildProductData();
            }

            @Override
            protected void onResponseFailed(Call<Product> call, Throwable t) {
                Toast.makeText(ProductDetailsActivity.this, "Request is failed!", Toast.LENGTH_SHORT).show();
            }
        });


    }



}
