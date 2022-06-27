package com.project.read_pro.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.project.read_pro.databinding.ActivityFirstLoadBinding;
import com.project.read_pro.model.Cart;
import com.project.read_pro.storage.LoginUtils;
import com.project.read_pro.utils.CartUtils;
import com.project.read_pro.view_model.CartViewModel;
import com.project.read_pro.view_model.ShowCurrentUserViewModel;

import java.util.List;

public class FirstLoadActivity extends AppCompatActivity {

    private ActivityFirstLoadBinding binding;
    private ShowCurrentUserViewModel showCurrentUserViewModel;
    private CartViewModel cartViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstLoadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
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
                if(LoginUtils.getInstance(this).isLoggedIn()){
                    cartViewModel.getProductsInCart(token).observe(this, cartResponse -> {
                        if(cartResponse != null){
                            List<Cart> carts = cartResponse.getCarts();
                            CartUtils.getInstance().setCarts(carts);
                        }
                        gotoMainActivity();
                    });
                }else {
                    gotoMainActivity();
                }
            }
        });
    }
    private void gotoMainActivity(){
        Intent intent = new Intent(FirstLoadActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}