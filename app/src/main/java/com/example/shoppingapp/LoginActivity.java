package com.example.shoppingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.databinding.ActivityLoginBinding;
import com.example.shoppingapp.model.Token;
import com.example.shoppingapp.model.User;
import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    @Override
    protected ActivityLoginBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityLoginBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // How to show verified email icon:
        // 1. Check email:
        //    1. Email needs to contain '@' and index of '@' should be over than 0  i@gmail.com
        //       Invalid input: @gmail.com
        //    2. Email needs to contain '.' after '@' index
        //       Invalid input: 1. test@.com  2.  test@gmail.
        // We check it in two place: 1. Login button clicked. 2. While input text by user.

        // Listen input text
        binding.emailEditText.addTextChangedListener(new TextWatcher() {

            // before text change
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("EditText", s.toString() + " start=" + start + ", count=" + count + ", after=" + after);
            }

            // on text change: s is input char
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("EditText", s.toString() + " start=" + start + ", count=" + count + ", before=" + before);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = s.toString();
                Log.d("EditText", email);

                /*if (email.contains("@") && email.indexOf('@') != 0) {
                    String tail = email.substring(email.indexOf('@'));
                    if (tail.contains(".")
                            && tail.indexOf('.') != 1
                            && email.lastIndexOf('.') != (email.length() - 1)) {
                        binding.icVerifiedEmail.setVisibility(View.VISIBLE);
                    } else {
                        binding.icVerifiedEmail.setVisibility(View.INVISIBLE);
                    }
                } else {
                    binding.icVerifiedEmail.setVisibility(View.INVISIBLE);
                }*/

                /* if (isEmailValid(email)) {
                    binding.icVerifiedEmail.setVisibility(View.VISIBLE);
                } else {
                    binding.icVerifiedEmail.setVisibility(View.INVISIBLE);
                }*/

                binding.icVerifiedEmail.setVisibility(isEmailValid(email) ? View.VISIBLE : View.INVISIBLE);

            }
        });


        binding.showHidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = binding.showHidePassword.isSelected();

                // current state: iSelected => true (shown password) next state: !iSelected => false (hide password)
                // current state: iSelected => false (hidden password) next state: !iSelected => true (show password)

                // initial states: isSelected=> false (password is hidden). When eye is clicked, change shown password mode.
                // current state: iSelected => false (hidden password) next state: !iSelected => true (show password)

                if (!isSelected) {
                    // show password
                    //binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    binding.passwordEdittext.setInputType(InputType.TYPE_CLASS_TEXT);

                } else {
                    // hide password
                    //binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.passwordEdittext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                binding.showHidePassword.setSelected(!isSelected);

            }
        });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = binding.emailEditText.getText().toString();
                String password = binding.passwordEdittext.getText().toString();

                // check if email is valid
                if (!isEmailValid(email))
                    return;


                Call<User> call = mainApi.login(new User(email, password));

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User user = response.body();
                            preferenceManager.setValue("isLoggedIn", true);
                            preferenceManager.setValue("email", email);
                            preferenceManager.setValue("password", password);

                            preferenceManager.setValue("firstName", user.getFirstName());
                            preferenceManager.setValue("lastName", user.getLastName());
                            preferenceManager.setValue("access_token", user.getAccessToken());
                            preferenceManager.setValue("user", user);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });


            }
        });

        binding.forgotPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    private boolean isEmailValid(String email) {
        boolean isValid = false;
        if (email.contains("@") && email.indexOf('@') != 0) {
            String tail = email.substring(email.indexOf('@'));
            if (tail.contains(".")
                    && tail.indexOf('.') != 1
                    && email.lastIndexOf('.') != (email.length() - 1)) {
                isValid = true;
            }
        }

        return isValid;
    }

}