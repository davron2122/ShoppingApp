package com.example.shoppingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.shoppingapp.R;
import com.example.shoppingapp.base.BaseViewHolder;
import com.example.shoppingapp.databinding.ItemOptionBinding;
import com.example.shoppingapp.fragments.OptionDialog;
import com.example.shoppingapp.model.ColorOption;
import com.example.shoppingapp.model.SizeOption;

import java.util.ArrayList;

public class OptionListAdapter extends  BaseAdapter{

    private ArrayList<ColorOption> colorOptionArrayList;
    private ArrayList<SizeOption> sizeOptionArrayList;

    private OptionDialog.OptionItemListener optionItemListener;

    private ColorOption selectColorOption;
    private SizeOption selectSizeOption;

    public void setSelectColorOption(ColorOption selectColorOption) {
        this.selectColorOption = selectColorOption;
    }

    public void setSelectSizeOption(SizeOption selectSizeOption) {
        this.selectSizeOption = selectSizeOption;
    }


    public void setOptionItemListener(OptionDialog.OptionItemListener optionItemListener) {
        this.optionItemListener = optionItemListener;
    }

    public OptionListAdapter(ArrayList<ColorOption> colorOptionArrayList, ArrayList<SizeOption> sizeOptionArrayList) {
        this.colorOptionArrayList = colorOptionArrayList;
        this.sizeOptionArrayList = sizeOptionArrayList;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOptionBinding binding = ItemOptionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new OptionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);

        holder.itemView.findViewById(R.id.tvOptionTitle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (optionItemListener != null) {
                    if (colorOptionArrayList != null && sizeOptionArrayList == null)
                        optionItemListener.onColorItemSelected(colorOptionArrayList.get(position));
                    else if (colorOptionArrayList == null && sizeOptionArrayList != null)
                        optionItemListener.onSizeItemSelected(sizeOptionArrayList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (colorOptionArrayList != null && sizeOptionArrayList == null)
            return colorOptionArrayList.size();
        else if (colorOptionArrayList == null && sizeOptionArrayList != null)
            return sizeOptionArrayList.size();
        else
            return 0;
    }

    public class OptionViewHolder extends BaseViewHolder<ItemOptionBinding> {
        public OptionViewHolder(ItemOptionBinding binding) {
            super(binding);
        }

        @Override
        public void onBind(int position) {
            if (colorOptionArrayList != null && sizeOptionArrayList == null) {
                ColorOption colorOption = colorOptionArrayList.get(position);
                binding.tvOptionTitle.setText(colorOption.getTitle());
                if (selectColorOption != null) {
//                    if (selectColorOption.getId() == colorOption.getId())
//                        binding.checked.setVisibility(View.VISIBLE);
//                    else
//                        binding.checked.setVisibility(View.VISIBLE);

                    binding.checked.setVisibility(selectColorOption.getId() == colorOption.getId() ? View.VISIBLE : View.INVISIBLE);
                }

            } else if (colorOptionArrayList == null && sizeOptionArrayList != null) {
                SizeOption sizeOption = sizeOptionArrayList.get(position);
                binding.tvOptionTitle.setText(sizeOption.getTitle());
                if (selectSizeOption != null) {
                    binding.checked.setVisibility(selectSizeOption.getId() == sizeOption.getId() ? View.VISIBLE : View.INVISIBLE);
                }
            }

        }
    }

}
