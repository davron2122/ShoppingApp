package com.example.shoppingapp.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

  private BaseAdapterListener baseAdapterListener;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);


    }

    public abstract class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    }

    public   abstract  void onBind(int position);

}
