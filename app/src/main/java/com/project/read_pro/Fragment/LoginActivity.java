package com.project.read_pro.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.project.read_pro.R;

public class LoginActivity extends AppCompatActivity {
    EditText editEmail,editPass;
    View textSignup ;
    View btnLogin ;
    String email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textSignup  = findViewById(R.id.text_signUP);
        btnLogin = findViewById(R.id.button_login);
        editEmail = findViewById(R.id.edit_email);
        editPass = findViewById(R.id.edit_pass);

        btnLogin.setOnClickListener(v -> checkLogin());

        textSignup.setOnClickListener(
                v -> openActivity2()
        );
    }

    private void checkLogin() {
        email = editEmail.getText().toString();
        pass = editPass.getText().toString();
        if(email.isEmpty()|| pass.isEmpty()){
            FailedSent();
        }else {
            sentLogin();
        }
    }

    private void FailedSent() {
        Toast.makeText(this,"Email and password is required",Toast.LENGTH_LONG).show();
    }
    private void sentLogin() {

        Toast.makeText(this,"Sent",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
        finish();
    }
    public void openActivity2() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }



}