package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.databinding.ActivityResetPasswordBinding;

public class ResetPasswordActivity extends BaseActivity<ActivityResetPasswordBinding> {
    @Override
    protected ActivityResetPasswordBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityResetPasswordBinding.inflate(inflater);


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPasswordActivity.this, PasswordChangedActivity.class);
                startActivity(intent);
            }
        });
    }
}
