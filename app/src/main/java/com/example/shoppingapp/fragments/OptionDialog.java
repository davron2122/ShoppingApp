package com.example.shoppingapp.fragments;

import static android.app.PendingIntent.getActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.shoppingapp.adapter.OptionListAdapter;
import com.example.shoppingapp.base.BaseDialog;
import com.example.shoppingapp.databinding.DialogColorBinding;
import com.example.shoppingapp.model.ColorOption;
import com.example.shoppingapp.model.SizeOption;

import java.util.ArrayList;



    // To do list!!


/*
1. Creating Dialog
    2. iflating the dialog layouts.
    3. creating and setting adapter for recycler view lists and setting layout managers.
            (size and color) and for defining titles when the dialog fragments exchange.
    4. Creating setters for Size and Colors we are calling data when needed.
            which can be seen in the ProductDetails Class.
 */
 public class OptionDialog extends BaseDialog<DialogColorBinding> {
    private OptionListAdapter optionListAdapter;
    private ArrayList<ColorOption> colorOptionArrayList;
    private ArrayList<SizeOption> sizeOptionArrayList;
    private OptionItemListener optionItemListener;

    private ColorOption selectColorOption;
    private SizeOption selectSizeOption;
    public void setSelectColorOption(ColorOption selectColorOption) {
        this.selectColorOption = selectColorOption;
    }

    public void setSelectSizeOption(SizeOption selectSizeOption) {
        this.selectSizeOption = selectSizeOption;
    }
    public void setOptionItemListener(OptionItemListener optionItemListener) {
        this.optionItemListener = optionItemListener;
    }

    public void setSizeOptionArrayList(ArrayList<SizeOption> sizeOptionArrayList) {
        this.sizeOptionArrayList = sizeOptionArrayList;
    }

    public void setColorOptionArrayList(ArrayList<ColorOption> colorOptionArrayList) {
        this.colorOptionArrayList = colorOptionArrayList;
    }

    @Override
    protected DialogColorBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean attachedToRoot) {
        return DialogColorBinding.inflate(inflater, parent, attachedToRoot) ;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (colorOptionArrayList != null)
            binding.title.setText("Color");
        else
            binding.title.setText("Size");
        optionListAdapter =new OptionListAdapter(colorOptionArrayList, sizeOptionArrayList);
        optionListAdapter.setSelectColorOption(selectColorOption);
        optionListAdapter.setSelectSizeOption(selectSizeOption);
        optionListAdapter.setOptionItemListener(new OptionItemListener() {
            @Override
            public void onColorItemSelected(ColorOption colorOption) {
                optionItemListener.onColorItemSelected(colorOption);
                dismiss();
            }

            @Override
            public void onSizeItemSelected(SizeOption sizeOption) {
                optionItemListener.onSizeItemSelected(sizeOption);
                dismiss();

            }
        });
        binding.optionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.optionRecyclerView.setAdapter(optionListAdapter);
    }
    public interface OptionItemListener {
        void onColorItemSelected(ColorOption colorOption);
        void onSizeItemSelected(SizeOption sizeOption);
    }
}

