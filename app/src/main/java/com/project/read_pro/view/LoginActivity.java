package com.project.read_pro.view;

import static com.project.read_pro.utils.Keyboard.hideKeyboard;
import static com.project.read_pro.utils.ProgressDialog.createAlertDialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.project.read_pro.databinding.ActivityLoginBinding;
import com.project.read_pro.model.Cart;
import com.project.read_pro.model.SaveProduct;
import com.project.read_pro.model.User;
import com.project.read_pro.storage.LoginUtils;
import com.project.read_pro.utils.CartUtils;
import com.project.read_pro.utils.SaveProductUtils;
import com.project.read_pro.view_model.CartViewModel;
import com.project.read_pro.view_model.LoginViewModel;
import com.project.read_pro.view_model.SaveProductViewModel;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;
    private CartViewModel cartViewModel;
    private SaveProductViewModel saveProductViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        saveProductViewModel = new ViewModelProvider(this).get(SaveProductViewModel.class);
        setUpListener();
    }

    private void setUpListener() {
        binding.textSignUP.setOnClickListener(view -> gotoRegisterActivity());
        binding.btnLogin.setOnClickListener(view -> loginUser());
    }


    private void loginUser(){

        hideKeyboard(this);

        String email = binding.editEmail.getText().toString();
        String password = binding.editPass.getText().toString();
        if(email.isEmpty()){
            binding.editEmail.setError("Email Required");
            binding.editEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            binding.editPass.setError("Password Required");
            binding.editPass.requestFocus();
            return;
        }
        AlertDialog alert = createAlertDialog(this);
        loginViewModel.getLoginResponseLiveData(email, password).observe(this, loginSignupResponse -> {
            if(loginSignupResponse != null) {
                if (!loginSignupResponse.isError()) {
                    User user = loginSignupResponse.getUser();
                    String token = loginSignupResponse.getToken();
                    LoginUtils.getInstance(this).saveUserInfo(user);
                    LoginUtils.getInstance(this).saveUserToken(token);
                    cartViewModel.getProductsInCart("Bearer " + token).observe(LoginActivity.this, cartResponse -> {
                        if (cartResponse != null) {
                            List<Cart> carts = cartResponse.getCarts();
                            CartUtils.getInstance().setCarts(carts);
                            saveProductViewModel.getSaveProduct("Bearer " + token).observe(LoginActivity.this, saveProductResponse -> {
                                if(saveProductResponse != null){
                                    List<SaveProduct> saveProducts = saveProductResponse.getSaveProducts();
                                    SaveProductUtils.getInstance().setSaveProducts(saveProducts);
                                    alert.dismiss();
                                    Toast.makeText(this, "Login successful.", Toast.LENGTH_SHORT).show();
                                    gotoMainActivity();
                                }
                            });
                        }
                    });
                } else {
                    alert.dismiss();
                    Toast.makeText(this, loginSignupResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void gotoRegisterActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    private void gotoMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}