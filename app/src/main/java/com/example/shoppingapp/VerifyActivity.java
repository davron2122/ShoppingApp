package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.databinding.ActivityVerifyBinding;

public class VerifyActivity extends BaseActivity<ActivityVerifyBinding> {
    @Override
    protected ActivityVerifyBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityVerifyBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerifyActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
        binding.verifyAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerifyActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}



