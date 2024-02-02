package com.example.shoppingapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.shoppingapp.base.BaseFragment;
import com.example.shoppingapp.databinding.FragmentCategoryBinding;

public class CategoryFragment extends BaseFragment<FragmentCategoryBinding> {
    @Override
    protected FragmentCategoryBinding inflateViewBinding(LayoutInflater layoutInflater, ViewGroup container, boolean toAttachParent) {
        return FragmentCategoryBinding.inflate(layoutInflater, container,toAttachParent);
    }
}
