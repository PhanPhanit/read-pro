package com.project.read_pro.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.project.read_pro.R;

import com.project.read_pro.utils.Http;

public class SplashScreen extends AppCompatActivity {
    Http http;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = getString(R.string.api_server)+"users/showMe";
                http = new Http(SplashScreen.this,url);
                http.setMethod("GET");
                http.setToken(true);
                http.sent();

                runOnUiThread(new Runnable() {
                    final Integer code = http.getStatusCode();
                    @Override
                    public void run() {

                        if(code==200){
                           // printSnake("200");
                            startActivity(new Intent( SplashScreen.this, MainActivity.class));
                            finish();
                        }else if(code==401){
                           // printSnake("401");
                            startActivity(new Intent( SplashScreen.this, LoginActivity.class));
                            finish();
                        }else {
                            printSnake("ERROR CODE"+code);
                        }

                    }
                });
            }
        }).start();

    }

    void printSnake(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}