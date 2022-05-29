package com.project.read_pro.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.project.read_pro.R;
public class Register extends AppCompatActivity {
    private View Textlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Textlogin = findViewById(R.id.text_login);
        Textlogin.setOnClickListener(
                v ->  finish()
        );
    }

}
