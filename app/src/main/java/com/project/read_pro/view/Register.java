package com.project.read_pro.view;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.read_pro.R;

import org.json.JSONException;
import org.json.JSONObject;

import com.project.read_pro.utils.Http;


public class Register extends AppCompatActivity {
    private View textLogin,btnRegister;
    private EditText et_username,et_phone,et_email,et_pass;
    private  String email, pass,username,phone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textLogin = findViewById(R.id.text_login);
        et_email = findViewById(R.id.edit_email);
        et_phone = findViewById(R.id.edit_phone);
        et_pass = findViewById(R.id.edit_pass);
        et_username = findViewById(R.id.edit_username);
        btnRegister = findViewById(R.id.button_create);

        textLogin.setOnClickListener(
                v ->  finish()
        );
        btnRegister.setOnClickListener(
                v ->  checkRegister()
        );
    }

    private void checkRegister() {
        email = et_email.getText().toString();
        pass = et_pass.getText().toString();
        phone = et_phone.getText().toString();
        username = et_username.getText().toString();
        if(email.isEmpty()|| pass.isEmpty() || phone.isEmpty()||username.isEmpty()){
            FailedSent();
        }else {

            sentRegister();
        }
    }

    private void FailedSent() {
        Toast.makeText(this,"Username Email Password and Phone are required",Toast.LENGTH_SHORT).show();
    }
    private void sentRegister() {
        JSONObject param = new JSONObject();
        try{
           param.put("name",username);
           param.put("email",email);
           param.put("password",pass);
        } catch ( JSONException e){
            e.printStackTrace();
        }
        String data = param.toString();
        String url = getString(R.string.api_server)+"auth/register";
        new Thread(new Runnable() {
            @Override
            public void run() {
               Http http = new Http(Register.this,url);
               http.setMethod("POST");
               http.setDate(data);
               http.sent();

               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       Integer code =  http.getStatusCode();
                       if(code==201|| code == 200){
                           alertSuccess();
                       }
                       else if(code == 422){
                           try {
                             JSONObject response = new JSONObject(http.getResponse());
                             String msg = response.getString("message");
                               alertFail();
                           }catch (JSONException e){
                               e.printStackTrace();
                           }
                       }
                       else {
                           Toast.makeText(Register.this,"error"+code,Toast.LENGTH_SHORT).show();
                       }
                   }
               });
            }
        }).start();

    }
    private void alertSuccess() {
        Toast.makeText(this,"Register Success",Toast.LENGTH_SHORT).show();
    }
    private void alertFail() {
        Toast.makeText(this,"Register Fail",Toast.LENGTH_SHORT).show();
    }

}
