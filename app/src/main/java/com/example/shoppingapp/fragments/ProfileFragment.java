package com.example.shoppingapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shoppingapp.LoginActivity;
import com.example.shoppingapp.base.BaseFragment;
import com.example.shoppingapp.databinding.FragmentProfileBinding;
import com.example.shoppingapp.model.User;

import java.io.File;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {
    private User user;
    @Override
    protected FragmentProfileBinding inflateView(LayoutInflater inflator, ViewGroup parent, boolean toAttach) {
        return FragmentProfileBinding.inflate(inflator, parent,toAttach);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String firstname = (String) parent.preferenceManager.getValue(String.class, "firstName", "Davronbek");
        binding.firstnamePf.setText(firstname);
        String lastname = (String) parent.preferenceManager.getValue(String.class, "lastName", "Eliboev");
        binding.lastnamePf.setText(lastname);

        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parent.preferenceManager.setValue("isLogin", false);
                String filename = "my_access_token.txt";

                Log.d("ProfileFragment", getContext().getFilesDir() + "/" + filename);

                File file = new File(getContext().getFilesDir(), filename);
                if (file.delete()) {
                    Log.d("ProfileFragment ", "Token file is deleted");
                }

                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
