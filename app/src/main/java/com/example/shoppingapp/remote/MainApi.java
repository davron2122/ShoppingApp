package com.example.shoppingapp.remote;

import com.example.shoppingapp.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

// MainApi -  public interface hisoblanadi.
public interface MainApi {

    //Base URL=http://dev-api.shoppingmall.beknumonov.com
    @POST("v1/user/")                           // user create qilish uchun @Post funksyadan foydalaniladi
    Call<User> createUser(@Body User user);     // <User> Call qilinib, @Body qismda User user #camelcase qilib yoziladi



    @PUT("v1/user/{id}")
    Call<User> updateUser(@Path("id") Integer userId, @Header("Authorization") String accessToken, @Body User user);

    @GET("v1/user/")
    Call<ArrayList<User>> getUserList (@Header("Authorization")String accessToken);

    @POST("v1/login/")
    Call<User> login (@Body User user);

}
