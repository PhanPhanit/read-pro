package com.project.read_pro.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.project.read_pro.response.LoginSignupResponse;
import com.project.read_pro.retrofit.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepository {
    private static final String TAG = RegisterRepository.class.getSimpleName();

    public LiveData<LoginSignupResponse> registerUser(String name, String email, String password){
        final MutableLiveData<LoginSignupResponse> mutableLiveData = new MutableLiveData<>();

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("name", name);
        jsonBody.addProperty("email", email);
        jsonBody.addProperty("password", password);

        RetrofitClient.getInstance().getApi().registerUser(jsonBody).enqueue(new Callback<LoginSignupResponse>() {
            @Override
            public void onResponse(Call<LoginSignupResponse> call, Response<LoginSignupResponse> response) {
                Log.d(TAG, "onResponse: Succeeded");
                LoginSignupResponse loginSignupResponse;
                if(response.code() == 201){
                    loginSignupResponse = response.body();
                }else{
                    String errorMessage = "Invalid information!";
                    try {
                        JSONObject jObError = new JSONObject(response.errorBody().string());
                        errorMessage = jObError.getString("message");
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                    loginSignupResponse = new LoginSignupResponse(errorMessage);
                }
                mutableLiveData.setValue(loginSignupResponse);

            }
            @Override
            public void onFailure(Call<LoginSignupResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
