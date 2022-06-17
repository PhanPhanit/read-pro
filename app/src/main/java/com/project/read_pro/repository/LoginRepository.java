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

public class LoginRepository {

    private static final String TAG = LoginRepository.class.getSimpleName();

    public LiveData<LoginSignupResponse> getLoginResponseData(String email, String password){
        final MutableLiveData<LoginSignupResponse> mutableLiveData = new MutableLiveData<>();

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("email", email);
        jsonBody.addProperty("password", password);

        RetrofitClient.getInstance().getApi().loginUser(jsonBody).enqueue(new Callback<LoginSignupResponse>() {
            @Override
            public void onResponse(Call<LoginSignupResponse> call, Response<LoginSignupResponse> response) {
                Log.d(TAG, "onResponse: Succeeded");
                LoginSignupResponse loginSignupResponse;
                if(response.code() == 200){
                    loginSignupResponse = response.body();
                }else{
                    String errorMessage = "Incorrect Information!";
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        errorMessage = jObjError.getString("message");
                    } catch (JSONException | IOException e) {
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
