package com.example.shoppingapp.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.bumptech.glide.Glide;
import com.example.shoppingapp.base.BaseFragment;
import com.example.shoppingapp.databinding.FragmentBannerBinding;
import com.example.shoppingapp.model.Banner;


public class BannerFragment extends BaseFragment<FragmentBannerBinding> {
    @Override
    protected FragmentBannerBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentBannerBinding.inflate(inflater, parent, toAttach);
    }

    private Banner banner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        banner = (Banner) bundle.getSerializable("banner");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glide.with(binding.bannerImage).load(banner.getImage()).into(binding.bannerImage);
    }
}
