package com.example.shoppingapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.shoppingapp.adapter.OrderedProductListAdapter;
import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.base.RequestCallback;
import com.example.shoppingapp.databinding.ActivityOrderDetailsBinding;
import com.example.shoppingapp.model.Order;
import com.example.shoppingapp.model.OrderedProduct;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/*
    1. Get Order data from intent ( getIntent().getSerializableExtra().
    2. Add order details (textview) to Order Details Page xml.
    3. Print Order details on textview. Order number, Order status etc
    4. Add RecyclerView
        1. Create List Adapter for ordered product list.
        2. Create ViewHolder for ordered products
        3. Set LayoutManger
        4. Set Adapter
*/
public class OrderDetailsActivity extends BaseActivity<ActivityOrderDetailsBinding> {

    private OrderedProductListAdapter adapter;
    private ArrayList<OrderedProduct> orderedProductArrayList;

    @Override
    protected ActivityOrderDetailsBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityOrderDetailsBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderedProductArrayList = new ArrayList<>();
        adapter = new OrderedProductListAdapter(orderedProductArrayList);

        binding.orderedProductRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.orderedProductRecyclerView.setAdapter(adapter);
        int orderId = getIntent().getIntExtra("order", 0);
        if (orderId != 0)
            getOrder(orderId);
    }

    private void getOrder(int orderId){
        Call<Order> call=mainApi.getOrder(orderId);

        call.enqueue(new RequestCallback<Order>() {
            @Override
            protected void onResponseSuccess(Call<Order> call, Response<Order> response) {
                onBind(response.body());
            }

            @Override
            protected void onResponseFailed(Call<Order> call, Throwable t) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void onBind(Order order) {
        binding.tvOrderNum.setText(order.getOrderNumber());
        binding.tvOrderStatus.setText(order.getOrderStatus());
        binding.tvPayment.setText(order.getPayment());
        binding.tvTotalPrice.setText(order.getTotalCurrentPrice().toString());
        binding.tvName.setText(order.getName());
        binding.tvPhoneNum.setText(order.getPhone());
        binding.tvAddress.setText(order.getAddress());
        orderedProductArrayList.addAll(order.getProducts());
        adapter.notifyDataSetChanged();

    }

}