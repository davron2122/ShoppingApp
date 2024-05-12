package com.example.shoppingapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.shoppingapp.OrderDetailsActivity;
import com.example.shoppingapp.adapter.CartListAdapter;
import com.example.shoppingapp.base.BaseFragment;
import com.example.shoppingapp.base.RequestCallback;
import com.example.shoppingapp.databinding.FragmentCartBinding;
import com.example.shoppingapp.model.Cart;
import com.example.shoppingapp.model.Order;
import com.example.shoppingapp.model.PreOrder;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/*

    TODO LIST
    1. Add RecyclerView to fragment_cart.xml  0
    2. Create CartListAdapter
        1. Create item_cart.xml       0
        2. Create cart array list     0
        3. Create CartViewHolder      0
        4. Bind()
    3. Create Cart Model              0
    4. Add method to MainApi.         0

    Inside CartFragment:
    5. onCreate()
        initialize cartArrayList and cartListAdapter  0
    6. onViewCreated()
        set LayoutManger to recyclerView.             0
        set adapter to recyclerView.                  0
    7. loadCarts()
        add carts to cartArrayList                    0
        adapter.notifyDataSetChange();                0

    8. Add bottom panel
        Add total price
        Add buy btn
    9. Add functionality to select box.

    10. Add select and unselect box in top

    11. Link with API ( Order API)
 */


public class CartFragment extends BaseFragment<FragmentCartBinding> {


    private ArrayList<Cart> cartArrayList;
    private CartListAdapter adapter;


    @Override
    protected FragmentCartBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentCartBinding.inflate(inflater, parent, toAttach);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartArrayList = new ArrayList<>();
        adapter = new CartListAdapter(cartArrayList);
        adapter.setCartSelectListener(new CartListAdapter.CartSelectListener() {
            @Override
            public void onSelectorChanged() {
                calculateTotalPrice();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        loadMyCart();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.cartRecyclerView.setAdapter(adapter);


        binding.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = binding.ivSelect.isSelected();
                binding.ivSelect.setSelected(!isSelected);
                for (Cart cart : cartArrayList) {
                    cart.setSelected(!isSelected);
                }
                adapter.notifyDataSetChanged();
                calculateTotalPrice();

            }
        });


        binding.buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> cartIds = new ArrayList<>();
                int count = 0;
                for (Cart cart : cartArrayList) {
                    if (cart.isSelected()) {
                        count++;
                        cartIds.add(cart.getId());
                    }
                }

                if (count == 0)
                    return;

                PreOrder preOrder = new PreOrder(cartIds);
                Call<Order> call = parent.mainApi.orderCarts(preOrder);
                call.enqueue(new RequestCallback<Order>() {
                    @Override
                    protected void onResponseSuccess(Call<Order> call, Response<Order> response) {
                        Order order = response.body();

                        Intent intent = new Intent(getContext(), OrderDetailsActivity.class);
                        intent.putExtra("order", order.getId());
                        startActivity(intent);

                    }

                    @Override
                    protected void onResponseFailed(Call<Order> call, Throwable t) {

                    }
                });


            }
        });
    }

    private void calculateTotalPrice() {
        int total_price = 0;
        int selectedCount = 0;
        for (Cart cart : cartArrayList) {
            if (cart.isSelected()) {
                int total_price_of_cart = cart.getProduct().getPriceCurrentAsInt() * cart.getQuantity();
                total_price += total_price_of_cart;
                selectedCount++;
            }
        }


        binding.ivSelect.setSelected(selectedCount == cartArrayList.size());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,### ₩");
        binding.totalPrice.setText(decimalFormat.format(total_price));
    }

    private void loadMyCart() {
        cartArrayList.clear();
        Call<ArrayList<Cart>> call = parent.mainApi.getMyCart();

        call.enqueue(new RequestCallback<ArrayList<Cart>>() {
            @Override
            protected void onResponseSuccess(Call<ArrayList<Cart>> call, Response<ArrayList<Cart>> response) {
                cartArrayList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            protected void onResponseFailed(Call<ArrayList<Cart>> call, Throwable t) {
                parent.showToast(t.getMessage());
            }
        });
    }
}
