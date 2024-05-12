package com.example.shoppingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;


import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.databinding.ActivityMainBinding;
import com.example.shoppingapp.fragments.CartFragment;
import com.example.shoppingapp.fragments.HomeFragment;
import com.example.shoppingapp.fragments.ProductsFragment;
import com.example.shoppingapp.fragments.ProfileFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private int selectedTab = R.id.homeTab;
    private HomeFragment homeFragment;
    private ProductsFragment productFragment;
    private CartFragment cartFragment;
    private ProfileFragment profileFragment;

    @Override
    protected ActivityMainBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityMainBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                replaceFragment(item.getItemId());

                return true;


            }
        });

        replaceFragment(R.id.homeTab);

    }

    private void replaceFragment(int tabId) {
        selectedTab = tabId;
        if (tabId == R.id.homeTab) {
            if (homeFragment == null)
                homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, homeFragment).commit();
            setTitle("Home");
        } else if (tabId == R.id.productsTab) {
            if (productFragment == null)
                productFragment = new ProductsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, productFragment).commit();
            setTitle("Products");
        } else if (tabId == R.id.cartTab) {
            if (cartFragment == null)
                cartFragment = new CartFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, cartFragment).commit();
            setTitle("Cart");
        } else if (tabId == R.id.profileTab) {
            if (profileFragment == null)
                profileFragment = new ProfileFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, profileFragment).commit();
            setTitle("Profile");


        }
    }
}