package com.example.shoppingapp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.viewbinding.ViewBinding;

import com.example.shoppingapp.model.ColorOption;
import com.example.shoppingapp.model.SizeOption;


    /*
 1. Creating Dialog fragment (by extending Base Dialog from Dialog Fragment)
 smaller version of the the fragment which help to creates small pop ups!

 2. Binding is done with parent.

 */

    public abstract class BaseDialog<VB extends ViewBinding> extends DialogFragment {

        protected VB binding;
        protected abstract VB inflateView(LayoutInflater inflater, ViewGroup parent, boolean attachedToRoot);


        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            binding = inflateView(inflater, container, false);
            return binding.getRoot();
        }


    }


