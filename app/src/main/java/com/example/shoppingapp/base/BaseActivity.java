package com.example.shoppingapp.base;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;

import com.example.shoppingapp.ApiService.ApiService;
import com.example.shoppingapp.R;


import com.example.shoppingapp.databinding.ActivityProductDetailsBinding;
import com.example.shoppingapp.model.ColorOption;
import com.example.shoppingapp.model.SizeOption;
import com.example.shoppingapp.remote.MainApi;
import com.example.shoppingapp.util.PreferenceManager;

import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//BaseActivity public abstract class bo'lib, istalgan nomli(misoluchun VB, SF) <VB extends Viewbinding> extends AppCompatActivity shaklda extend boladi.
public abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity {
    protected T binding;
    private static String BASE_URL = "http://api.annyong.store";

    public PreferenceManager preferenceManager;

    public MainApi mainApi;
    private Toolbar toolbar;
    private TextView tvPageTitle;


    protected abstract T inflateViewBinding(LayoutInflater inflater);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = inflateViewBinding(getLayoutInflater());
        setContentView(binding.getRoot());
        mainApi = ApiService.provideApi(MainApi.class, this);
        preferenceManager = PreferenceManager.getInstance(this);

        toolbar = binding.getRoot().findViewById(R.id.toolbar);
        tvPageTitle = binding.getRoot().findViewById(R.id.pageTitle);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        if (hasBackButton()) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }

        }
    }


    protected boolean hasBackButton() {
        return false;
    }

    protected void setTitle(String title) {

        if (tvPageTitle != null) {
            tvPageTitle.setText(title);
        }
    }

    public void showToast(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }



}