package com.example.shoppingapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.databinding.ActivityForgotPasswordBinding;

public class ForgotPasswordActivity extends BaseActivity<ActivityForgotPasswordBinding> {


    @Override
    protected ActivityForgotPasswordBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityForgotPasswordBinding.inflate(inflater);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.sendCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, VerifyActivity.class);
                startActivity(intent);
            }
        });
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }



}
