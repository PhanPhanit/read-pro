package com.project.read_pro.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.project.read_pro.databinding.ActivityFirstLoadBinding;
import com.project.read_pro.storage.LoginUtils;
import com.project.read_pro.view_model.ShowCurrentUserViewModel;

public class FirstLoadActivity extends AppCompatActivity {

    private ActivityFirstLoadBinding binding;
    ShowCurrentUserViewModel showCurrentUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstLoadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showCurrentUserViewModel = new ViewModelProvider(this).get(ShowCurrentUserViewModel.class);

        showCurrentUser();
    }

    private void showCurrentUser() {
        String token = "Bearer " + LoginUtils.getInstance(this).getUserToken();
        showCurrentUserViewModel.ShowCurrentUser(token).observe(this, showCurrentUserResponse -> {
            if(showCurrentUserResponse != null){
                if(showCurrentUserResponse.getCode() == 200){
                    LoginUtils.getInstance(this).saveUserInfo(showCurrentUserResponse.getUser());
                }else{
                    LoginUtils.getInstance(this).clearAll();
                }
            }
            Intent intent = new Intent(FirstLoadActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }
}