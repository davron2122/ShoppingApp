package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.databinding.ActivitySignUpBinding;
import com.example.shoppingapp.model.User;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends BaseActivity<ActivitySignUpBinding> {

    private String deviceToken;

    @Override
    protected ActivitySignUpBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivitySignUpBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.emailEditText.getText().toString();
                String password = binding.passwordEdittext.getText().toString();
                String firstName = binding.firstNameEditText.getText().toString();
                String lastName = binding.lastNameEditText.getText().toString();
                String phoneNumber = binding.phoneNumberEditText.getText().toString();
                String postCode = binding.postCodeEditText.getText().toString();
                String address = binding.addressEditText.getText().toString();
                if (!email.isEmpty() && !password.isEmpty()) {

                    User user = new User(email, password, firstName, lastName, phoneNumber, postCode, address, deviceToken);

                    Log.d("User", new Gson().toJson(user));

                    Call<User> call = mainApi.createUser(user);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User createdUser = response.body();
                            preferenceManager.setValue("isLogin", true);
                            preferenceManager.setValue("access_token", createdUser.getAccessToken());

                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });


                }


            }
        });

    }
}
