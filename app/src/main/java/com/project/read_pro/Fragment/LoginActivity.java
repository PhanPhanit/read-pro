package com.project.read_pro.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.project.read_pro.R;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        View textSignup = findViewById(R.id.text_signUP);

        textSignup.setOnClickListener(
                v -> openActivity2()
        );
    }
    public void openActivity2() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}