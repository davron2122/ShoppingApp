package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.databinding.ActivitySplashBinding;
import com.example.shoppingapp.fragments.HomeFragment;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {


    private boolean isLogin=false;

    @Override
    protected ActivitySplashBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivitySplashBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        //SharedPreferences sharedPreferences = getSharedPreferences("NoteApp", Context.MODE_PRIVATE);
        isLogin=(Boolean) preferenceManager.getValue(Boolean.class, "isLogin",false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLogin) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        },3000);

    }


}
