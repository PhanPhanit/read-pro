package com.project.read_pro.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.project.read_pro.R;

import org.json.JSONException;
import org.json.JSONObject;

import com.project.read_pro.utils.Http;
import com.project.read_pro.storage.LocalStorage;

public class LoginActivity extends AppCompatActivity {
   private EditText editEmail,editPass;
   private View textSignup,forget ;
   private View btnLogin ;
   private String email,pass;
   String token = "";
   LocalStorage localStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        localStorage = new LocalStorage(LoginActivity.this);
        textSignup  = findViewById(R.id.text_signUP);
        btnLogin = findViewById(R.id.button_login);
        editEmail = findViewById(R.id.edit_email);
        editPass = findViewById(R.id.edit_pass);
        forget = findViewById(R.id.text_proget_password);
        btnLogin.setOnClickListener(v ->
                checkLogin()
        );

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
        //Toast.makeText(this,"Sent",Toast.LENGTH_SHORT).show();
        JSONObject param = new JSONObject();
        try{
            param.put("email",email);
            param.put("password",pass);

        }catch (JSONException e){
            e.printStackTrace();
        }
        String data = param.toString();
        String url = getString(R.string.api_server)+"auth/login";
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(LoginActivity.this,url);
                http.setMethod("post");
                http.setDate(data);
                http.sent();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if(code==201){
                            try{
                                JSONObject response = new JSONObject(http.getResponse());
                                String token = response.getString("token");

                                localStorage.setToken(token);


                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();

                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        else if(code == 422) {
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String msg = response.getString("message");
                                alertFail(msg);
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        else if(code == 401 ){
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String msg = response.getString("message");
                                alertFail(msg);
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        else {
                            alertFail("Error"+code);
                        }
                    }
                });
            }
        }).start();



    }

    private void alertFail(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    private void alertSuccess(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    public void openActivity2() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }



}