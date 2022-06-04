package com.project.read_pro.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorage {

    SharedPreferences sharedPreferences ;
    Context context;
    String token;

    public LocalStorage(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("STORAGE_LOGIN_API",Context.MODE_PRIVATE);
    }

    public String getToken() {
        token = sharedPreferences.getString("TOKEN","");
        return token;
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("TOKEN",token);
        editor.apply();
        this.token = token;
    }
}
