package com.project.read_pro.view;
import static com.project.read_pro.utils.Keyboard.hideKeyboard;
import static com.project.read_pro.utils.ProgressDialog.createAlertDialog;
import static com.project.read_pro.utils.Validation.isValidEmail;
import static com.project.read_pro.utils.Validation.isValidPassword;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.project.read_pro.R;
import com.project.read_pro.databinding.ActivityRegisterBinding;
import com.project.read_pro.model.User;
import com.project.read_pro.response.LoginSignupResponse;
import com.project.read_pro.storage.LoginUtils;
import com.project.read_pro.view_model.RegisterViewModel;


public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);


        setUpListener();
    }

    private void setUpListener() {
        binding.btnRegister.setOnClickListener(view -> registerUser());
    }

    private void registerUser() {
        hideKeyboard(this);
        String name = binding.editUsername.getText().toString();
        String email = binding.editEmail.getText().toString();
        String password = binding.editPass.getText().toString();

        if(name.isEmpty()){
            binding.editUsername.setError("Name required!");
            binding.editUsername.requestFocus();
            return;
        }
        if(email.isEmpty()){
            binding.editEmail.setError("Email required!");
            binding.editEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            binding.editPass.setError("Password required!");
            binding.editPass.requestFocus();
            return;
        }

        if(isValidEmail(email)){
            binding.editEmail.setError("Invalid email!");
            binding.editEmail.requestFocus();
            return;
        }
        if(isValidPassword(password)){
            binding.editPass.setError("Invalid password!");
            binding.editPass.requestFocus();
            return;
        }

        AlertDialog alert = createAlertDialog(this);

        registerViewModel.registerUser(name, email, password).observe(this, new Observer<LoginSignupResponse>() {
            @Override
            public void onChanged(LoginSignupResponse loginSignupResponse) {
                if(!loginSignupResponse.isError()){
                    User user = loginSignupResponse.getUser();
                    String token = loginSignupResponse.getToken();
                    LoginUtils.getInstance(RegisterActivity.this).saveUserInfo(user);
                    LoginUtils.getInstance(RegisterActivity.this).saveUserToken(token);
                    Toast.makeText(RegisterActivity.this, "Register successfully.", Toast.LENGTH_SHORT).show();
                    alert.dismiss();
                    gotoMainActivity();
                }else{
                    alert.dismiss();
                    Toast.makeText(RegisterActivity.this, loginSignupResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    private void gotoLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
