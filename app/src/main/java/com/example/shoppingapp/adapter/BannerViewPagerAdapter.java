package com.example.shoppingapp.adapter;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.shoppingapp.fragments.BannerFragment;
import com.example.shoppingapp.model.Banner;

import java.util.ArrayList;

public class BannerViewPagerAdapter extends FragmentStateAdapter {
    private ArrayList<Banner> bannerArrayList;

    public BannerViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<Banner> bannerArrayList) {
        super(fragmentActivity);
        this.bannerArrayList = bannerArrayList;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Banner banner = bannerArrayList.get(position);
        BannerFragment bannerFragment = new BannerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("banner", banner);
        bannerFragment.setArguments(bundle);
        return bannerFragment;
    }

    @Override
    public int getItemCount() {
        return bannerArrayList.size();
    }
}
