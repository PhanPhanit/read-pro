package com.project.read_pro.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.project.read_pro.MainActivity;
import com.project.read_pro.R;

import org.json.JSONException;
import org.json.JSONObject;

import Data.HTTP;
import Data.LocalStorage;

public class SplashScreen extends AppCompatActivity {
    Context context ;
    LocalStorage localStorage;
    HTTP http;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = getString(R.string.api_server)+"users/showMe";

                http = new HTTP(SplashScreen.this,url);
                http.setMethod("GET");
                http.sent();

                runOnUiThread(new Runnable() {
                    final Integer code = http.getStatusCode();
                    @Override
                    public void run() {

                        if(code==200){
                            printSnake("201");
                            startActivity(new Intent( SplashScreen.this, MainActivity.class));
                            finish();
                        }else if(code==401){
                            printSnake("401");
                            startActivity(new Intent( SplashScreen.this, LoginActivity.class));
                            finish();
                        }else {
                            printSnake("ERROR CODE"+code);
                        }

                    }
                });
            }
        }).start();



//
//        new Handler().postDelayed(() -> {
//            startActivity(new Intent( SplashScreen.this, LoginActivity.class));
//            finish();
//        },1500);
    }

    void printSnake(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}