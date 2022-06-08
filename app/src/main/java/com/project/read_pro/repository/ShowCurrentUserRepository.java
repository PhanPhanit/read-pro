package com.project.read_pro.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.read_pro.response.ShowCurrentUserResponse;
import com.project.read_pro.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowCurrentUserRepository {
    public static final String TAG = ShowCurrentUserRepository.class.getSimpleName();

    public LiveData<ShowCurrentUserResponse> showCurrentUser(String token){
        final MutableLiveData<ShowCurrentUserResponse> mutableLiveData = new MutableLiveData<>();
        RetrofitClient.getInstance().getApi().showCurrentUser(token).enqueue(new Callback<ShowCurrentUserResponse>() {
            @Override
            public void onResponse(Call<ShowCurrentUserResponse> call, Response<ShowCurrentUserResponse> response) {
                Log.d(TAG, "onResponse: Succeeded" + response.code());
                ShowCurrentUserResponse showCurrentUserResponse;
                if(response.code() == 200){
                    showCurrentUserResponse = response.body();
                    showCurrentUserResponse.setCode(200);
                }else{
                    showCurrentUserResponse = new ShowCurrentUserResponse(response.code());
                }
                mutableLiveData.setValue(showCurrentUserResponse);
            }

            @Override
            public void onFailure(Call<ShowCurrentUserResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
