package com.example.shoppingapp.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.base.BaseFragment;
import com.example.shoppingapp.databinding.FragmentImageBinding;

public class ImageFragment extends BaseFragment<FragmentImageBinding> {
    @Override
    protected FragmentImageBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentImageBinding.inflate(inflater, parent, toAttach);
    }

    private String imageUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        imageUrl = bundle.getString("imageUrl");

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glide.with(binding.image).load(imageUrl).into(binding.image);
    }
}
