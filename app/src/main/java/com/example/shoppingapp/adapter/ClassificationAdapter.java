package com.example.shoppingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.example.shoppingapp.base.BaseAdapterListener;
import com.example.shoppingapp.base.BaseViewHolder;
import com.example.shoppingapp.databinding.ItemCategoryBinding;
import com.example.shoppingapp.model.Category;
import com.example.shoppingapp.model.Classification;
import com.example.shoppingapp.model.Subproduct;

import java.util.ArrayList;

public class ClassificationAdapter extends BaseAdapter {



    private ArrayList<Classification> classificationArrayList;
    private ArrayList<Category> categoryArrayList;
    private ArrayList<Subproduct> subproductArrayList;
    private BaseAdapterListener listener;
    public void setListener(BaseAdapterListener listener) {
        this.listener = listener;
    }

    public enum Type {
        CLASSIFICATION, CATEGORY, SUBPRODUCT
    }

    private Type type = Type.CLASSIFICATION;

    public void changeType(Type type){
        this.type=type;
        notifyDataSetChanged();
    }
    public Type moveBack() {
        if (type == Type.SUBPRODUCT) {
            changeType(Type.CATEGORY);
        } else if (type == Type.CATEGORY) {
            changeType(Type.CLASSIFICATION);
        }
        return type;
    }


    public ClassificationAdapter(ArrayList<Classification> classificationArrayList, ArrayList<Category> categoryArrayList, ArrayList<Subproduct> subproductArrayList) {
        this.classificationArrayList = classificationArrayList;
        this.categoryArrayList = categoryArrayList;
        this.subproductArrayList = subproductArrayList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
        holder.setBaseAdapterListener(listener);

    }

    @Override
    public int getItemCount() {
        if (type == Type.SUBPRODUCT){
            return  subproductArrayList.size();
        }else if (type==Type.CATEGORY){
            return categoryArrayList.size();
        }else{
            return classificationArrayList.size();
        }
    }


    class ItemViewHolder extends BaseViewHolder<ItemCategoryBinding> {
        public ItemViewHolder(@NonNull ItemCategoryBinding binding) {
            super(binding);
        }

        @Override
        public void onBind(int position) {
            if (type == Type.CLASSIFICATION) {
                Classification classification = classificationArrayList.get(position);
                binding.tvTitle.setText(classification.getTitle());

            } else if (type == Type.CATEGORY) {
                Category category = categoryArrayList.get(position);
                binding.tvTitle.setText(category.getTitle());

            } else if (type == Type.SUBPRODUCT) {
                Subproduct subproduct=subproductArrayList.get(position);
                binding.tvTitle.setText(subproduct.getTitle());
            }

            binding.tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int id = type == Type.CLASSIFICATION ? classificationArrayList.get(position).getId() : (type == Type.CATEGORY ? categoryArrayList.get(position).getId() : subproductArrayList.get(position).getId());
                        String title = type == Type.SUBPRODUCT ? subproductArrayList.get(position).getTitle() : "";
                        listener.onCategoryClick(id, title, type);
                    }
                }
            });
        }

    }

}
