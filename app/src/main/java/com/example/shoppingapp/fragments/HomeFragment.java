package com.example.shoppingapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shoppingapp.adapter.BannerViewPagerAdapter;


import com.example.shoppingapp.adapter.IndicatorAdapter;
import com.example.shoppingapp.adapter.ProductListAdapter;
import com.example.shoppingapp.base.BaseFragment;
import com.example.shoppingapp.databinding.FragmentHomeBinding;

import com.example.shoppingapp.model.Banner;
import com.example.shoppingapp.model.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private ArrayList<Banner> bannerArrayList;
    private ArrayList<Product> productArrayList;

    private BannerViewPagerAdapter bannerViewPagerAdapter;

    private IndicatorAdapter indicatorAdapter;
    private ProductListAdapter productListAdapter;

    @Override
    protected FragmentHomeBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentHomeBinding.inflate(inflater, parent, toAttach);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bannerArrayList = new ArrayList<>();
        productArrayList = new ArrayList<>();
        bannerViewPagerAdapter = new BannerViewPagerAdapter(getActivity(), bannerArrayList);
        indicatorAdapter = new IndicatorAdapter(bannerArrayList.size());
        productListAdapter = new ProductListAdapter(productArrayList);

        loadBanners();
        loadPopularProductList();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.bannerViewPager.setAdapter(bannerViewPagerAdapter);

        binding.pagerIndicatorView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        binding.pagerIndicatorView.setAdapter(indicatorAdapter);


        binding.popularProductRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.popularProductRecyclerView.setAdapter(productListAdapter);


        binding.bannerViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                indicatorAdapter.setSelectedDotPosition(position);
                indicatorAdapter.notifyDataSetChanged();
            }
        });

    }

    private void loadBanners() {

        bannerArrayList.clear();
        Call<ArrayList<Banner>> call = parent.mainApi.getBanners();

        call.enqueue(new Callback<ArrayList<Banner>>() {
            @Override
            public void onResponse(Call<ArrayList<Banner>> call, Response<ArrayList<Banner>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    bannerArrayList.addAll(response.body());
                    bannerViewPagerAdapter.notifyDataSetChanged();
                    indicatorAdapter.setSize(bannerArrayList.size());
                    indicatorAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Banner>> call, Throwable t) {

            }
        });
    }

    private void loadPopularProductList() {

        Call<ArrayList<Product>> call = parent.mainApi.getPopularProducts();

        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    productArrayList.addAll(response.body());
                    productListAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

    }
}
