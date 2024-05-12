package com.example.shoppingapp.ApiService;

import android.content.Context;


import com.example.shoppingapp.MainActivity;
import com.example.shoppingapp.model.User;
import com.example.shoppingapp.remote.MainApi;
import com.example.shoppingapp.util.PreferenceManager;
import com.google.firebase.encoders.json.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiService {
    private static String baseUrl = "http://api.annyong.store";

    private static Retrofit mRetrofit;

    private static Retrofit proviceRetrofit(Context context) {

        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(proviceOkHttpClient(context))
                    .build();
        }


        return mRetrofit;
    }

    private static OkHttpClient proviceOkHttpClient(Context context) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        boolean isDevelopment = BuildConfig.DEBUG;
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient().newBuilder()
                .addInterceptor(logging)
                .addInterceptor(provideAccessTokenInterceptor(context))
                .build();
    }

    private static Interceptor provideAccessTokenInterceptor(Context context) {

        return chain -> {

            Request original = chain.request();

            HttpUrl originalUrl = original.url();


            HttpUrl.Builder urlBuilder = originalUrl.newBuilder();

            HttpUrl url = urlBuilder.build();

            Request.Builder requestBuilder = original.newBuilder().url(url);

            if (context != null) {
                String access_token = (String) PreferenceManager.getInstance(context).getValue(String.class, "access_token", "");

                if (!access_token.isEmpty()) {
                    requestBuilder.addHeader("Authorization", "Bearer " + access_token);
                }
            }

            Request request = requestBuilder.build();

            Response response = chain.proceed(request);

            return createWellDoneResponse(response, chain, requestBuilder, context);

            //return response;
        };
    }

    private static Response createWellDoneResponse(Response response, Interceptor.Chain chain, Request.Builder requestBuilder, Context context) {

        if (response.code() == 401) {  // check if response code is unathorized token

            PreferenceManager preferenceManager = PreferenceManager.getInstance(context);
            String email = (String) preferenceManager.getValue(String.class, "email", "");
            String password = (String) preferenceManager.getValue(String.class, "password", "");

            User user = new User(email, password);
            try {
                retrofit2.Response<User> loginResponse = ApiService.provideApi(MainApi.class, context).login(user).execute();

                if (loginResponse.code() == 200) {
                    user = loginResponse.body();

                    preferenceManager.setValue("access_token", user.getAccessToken());
                    requestBuilder.removeHeader("Authorization");
                    requestBuilder.addHeader("Authorization", "Bearer " + user.getAccessToken());
                    response.close();
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return response;

    }

    public static <T> T provideApi(Class<T> services, Context context) {
        return proviceRetrofit(context).create(services);
    }
}
