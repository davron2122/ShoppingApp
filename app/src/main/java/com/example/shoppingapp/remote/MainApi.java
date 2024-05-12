package com.example.shoppingapp.remote;

import com.example.shoppingapp.model.Banner;
import com.example.shoppingapp.model.Cart;
import com.example.shoppingapp.model.CartRequest;
import com.example.shoppingapp.model.Category;
import com.example.shoppingapp.model.Classification;
import com.example.shoppingapp.model.Order;
import com.example.shoppingapp.model.PreOrder;
import com.example.shoppingapp.model.Product;
import com.example.shoppingapp.model.Subproduct;
import com.example.shoppingapp.model.User;
import com.google.gson.JsonObject;

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

    @GET("/v1/user/verify_email/")
    Call<JsonObject> requestVerifyEmail(@Query("email") String email);

    //home page starts
    @GET("/v1/banner/")
    Call<ArrayList<Banner>> getBanners();

    @GET("/v1/popular/")
    Call<ArrayList<Product>> getPopularProducts();


    //Classification, category

    @GET("/v1/classification/")
    Call<ArrayList<Classification>> getClassifications();

    @GET("/v1/classification/{id}/category/")
    Call<ArrayList<Category>> getCategoryWithClassification(@Path("id") int classificationId);

    @GET("/v1/category/{id}/subproduct/")
    Call<ArrayList<Subproduct>> getSubproductWithCategory(@Path("id") int categoryId);




    // Products

    @GET("/v1/subproduct/{id}/products/")
    Call<ArrayList<Product>> getProducts(@Path("id") int subproductId);

    @GET("/v1/product/{id}/")
    Call<Product> getProductDetails(@Path("id") int productId);

    @POST("/v1/cart/")
    Call<CartRequest> addProductToCart(@Body CartRequest cartRequest);

    @GET("/v1/cart/")
    Call<ArrayList<Cart>> getMyCart();

    @POST("/v1/cart/order/")
    Call<Order> orderCarts(@Body PreOrder preOrder);
    @GET("/v1/order/{id}/")
    Call<Order> getOrder(@Path("id")int id);

//    @POST("/v1/cart/order/")
//    Call<Order> orderCarts(@Body PreOrder preOrder);
}


