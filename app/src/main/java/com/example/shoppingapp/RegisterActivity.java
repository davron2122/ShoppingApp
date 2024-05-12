package com.example.shoppingapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.databinding.ActivityRegisterBinding;
import com.example.shoppingapp.model.User;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> {
    @Override
    protected ActivityRegisterBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityRegisterBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /*Typing email and check real time if email is valid or not*/
        binding.etEmail.addTextChangedListener(new TextWatcher() {

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
                binding.icVerifiedEmail.setVisibility(isEmailValid(email) ? View.VISIBLE : View.INVISIBLE);

            }
        });

        /*When back button is clicked*/
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*When eye of password is clicked*/
        binding.showHidePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = binding.showHidePasswordBtn.isSelected();

                // current state: iSelected => true (shown password) next state: !iSelected => false (hide password)
                // current state: iSelected => false (hidden password) next state: !iSelected => true (show password)

                // initial states: isSelected=> false (password is hidden). When eye is clicked, change shown password mode.
                // current state: iSelected => false (hidden password) next state: !iSelected => true (show password)

                if (!isSelected) {
                    // show password
                    //binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    binding.etPassword.setInputType(InputType.TYPE_CLASS_TEXT);

                } else {
                    // hide password
                    //binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                binding.showHidePasswordBtn.setSelected(!isSelected);

            }
        });

        /*When eye of confirm password is clicked*/
        binding.showHidePasswordBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = binding.showHidePasswordBtn1.isSelected();

                // current state: iSelected => true (shown password) next state: !iSelected => false (hide password)
                // current state: iSelected => false (hidden password) next state: !iSelected => true (show password)

                // initial states: isSelected=> false (password is hidden). When eye is clicked, change shown password mode.
                // current state: iSelected => false (hidden password) next state: !iSelected => true (show password)

                if (!isSelected) {
                    // show password
                    //binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    binding.etConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT);

                } else {
                    // hide password
                    //binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.etConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                binding.showHidePasswordBtn1.setSelected(!isSelected);

            }
        });

        /*When Get postcode btn is clicked*/
        binding.btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, AddressActivity.class);
                activityResultLauncher.launch(intent);
            }
        });
        /*When click agreement */
        binding.agreementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = binding.agreementBtn.isSelected();
                binding.agreementBtn.setSelected(!isSelected);
            }
        });

        /*When complete btn is clicked*/
        binding.btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.etEmail.getText().toString();
                String firstName = binding.etFirstName.getText().toString();
                String lastName = binding.etLastName.getText().toString();
                String password = binding.etPassword.getText().toString();
                String confirmPassword = binding.etConfirmPassword.getText().toString();
                String phoneNumber = binding.etPhoneNumber.getText().toString();
                String postCode = binding.tvPostCode.getText().toString();
                String address = binding.tvAddress.getText().toString();
                String addressDetails = binding.etAddressDetails.getText().toString();

                if (!isEmailValid(email)) {
                    binding.etEmail.setError("Email is not valid");
                    return;
                }

                if (firstName.isEmpty()) {
                    binding.etFirstName.setError("Required");
                    return;
                }
                if (phoneNumber.isEmpty()) {
                    binding.etPhoneNumber.setError("Required");
                    return;
                }

                if (lastName.isEmpty()) {
                    binding.etLastName.setError("Required");
                    return;
                }

                if (password.isEmpty()) {
                    binding.etPassword.setError("Required");
                    return;
                }
                if (confirmPassword.isEmpty()) {
                    binding.etConfirmPassword.setError("Required");
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    binding.etConfirmPassword.setError("Passwords should be the same");
                    return;
                }

                if (postCode.isEmpty() || address.isEmpty()) {
                    binding.tvPostCode.setError("Required");
                    binding.tvAddress.setError("Required");
                    return;
                }

                if (addressDetails.isEmpty()) {
                    binding.etAddressDetails.setError("Required");
                    return;
                }


                String full_address = "[" + postCode + "] " + address + ", " + addressDetails;
                User user = new User(email, password, firstName, lastName, phoneNumber, full_address);
                Log.d("User", new Gson().toJson(user));
                Call<User> call = mainApi.createUser(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User newUser = response.body();
                            preferenceManager.setValue("isLoggedIn", true);
                            preferenceManager.setValue("access_token", newUser.getAccessToken());
                            preferenceManager.setValue("id", newUser.getId());
                            preferenceManager.setValue("firstname", newUser.getFirstName());
                            preferenceManager.setValue("lastname", newUser.getLastName());
                            preferenceManager.setValue("email", newUser.getEmail());
                            preferenceManager.setValue("password", password);
                            preferenceManager.setValue("phoneNumber", newUser.getPhoneNumber());
                            preferenceManager.setValue("address", newUser.getAddress());
                            moveToMain();
                            // preferenceManager.setValue("refresh_token", newUser.getReresh_token());

                            CharSequence text = "Registration is completed";
                            int duration = Toast.LENGTH_LONG;
                            Toast toast = Toast.makeText(RegisterActivity.this, text, duration);
                            toast.show();

                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);

                        }else {
                            Log.d("User", "Error receiving response from API");
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                    }
                });
                //passwordError set VISIBLE
                //signupError set VISIBLE
            }
        });
        binding.btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, AddressActivity.class);
                activityResultLauncher.launch(intent);
            }
        });
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void moveToMain() {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        String postcode = data.getStringExtra("postcode");
                        String address = data.getStringExtra("address");
                        binding.tvPostCode.setText(postcode);
                        binding.tvAddress.setText(address);
                    }
                }
            }
    );
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