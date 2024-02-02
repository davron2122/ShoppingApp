package com.example.shoppingapp.base;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;

import com.example.shoppingapp.ApiService.ApiService;
import com.example.shoppingapp.R;
import com.example.shoppingapp.database.DatabaseHelper;

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
    public DatabaseHelper databaseHelper;






    public MainApi mainApi;


    protected abstract T inflateViewBinding(LayoutInflater inflater);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = inflateViewBinding(getLayoutInflater());
        setContentView(binding.getRoot());
        mainApi = ApiService.provideApi(MainApi.class, this);
        preferenceManager = PreferenceManager.getInstance(this);





            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient().newBuilder().
                    addInterceptor(logging).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            mainApi = retrofit.create(MainApi.class);


        }


    }




