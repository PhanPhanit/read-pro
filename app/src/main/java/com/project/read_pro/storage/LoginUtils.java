package com.project.read_pro.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.project.read_pro.model.User;
import com.project.read_pro.response.LoginSignupResponse;

public class LoginUtils {
    private static final String SHARED_PREF_NAME = "shared_preference";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String GOOGLE_ID = "googleId";
    private static final String FACEBOOK_ID = "facebookId";
    private static final String ROLE = "role";
    private static final String IS_ACTIVE = "isActive";
    private static final String TOKEN = "token";

    private static LoginUtils mInstance;
    private final Context mCtx;

    private LoginUtils(Context mCtx){
        this.mCtx = mCtx;
    }

    public static synchronized LoginUtils getInstance(Context mCtx){
        if(mInstance == null){
            mInstance = new LoginUtils(mCtx);
        }
        return mInstance;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(ID, -1) != -1;
    }

    public void saveUserInfo(User user){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(ID, user.getId());
        editor.putString(NAME, user.getName());
        editor.putString(EMAIL, user.getEmail());
        editor.putString(GOOGLE_ID, user.getGoogleId());
        editor.putString(FACEBOOK_ID, user.getFacebookId());
        editor.putString(ROLE, user.getRole());
        editor.putBoolean(IS_ACTIVE, user.isActive());
        editor.apply();
    }

    public User getUserInfo(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(ID, -1),
                sharedPreferences.getString(NAME, null),
                sharedPreferences.getString(EMAIL, null),
                sharedPreferences.getString(ROLE, null),
                sharedPreferences.getString(FACEBOOK_ID, null),
                sharedPreferences.getString(GOOGLE_ID, null),
                sharedPreferences.getBoolean(IS_ACTIVE, false)
        );
    }

    public void saveUserToken(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public String getUserToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TOKEN, "");
    }

    public void clearAll(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
        editor.apply();
    }


}
