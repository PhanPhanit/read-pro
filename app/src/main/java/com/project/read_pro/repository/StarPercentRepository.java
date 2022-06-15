package com.project.read_pro.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.read_pro.response.StarPercentResponse;
import com.project.read_pro.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarPercentRepository {
    public static final String TAG = StarPercentRepository.class.getSimpleName();

    public LiveData<StarPercentResponse> getProductStarPercent(int productId){
        final MutableLiveData<StarPercentResponse> mutableLiveData = new MutableLiveData<>();
        RetrofitClient.getInstance().getApi().getProductStarPercent(productId).enqueue(new Callback<StarPercentResponse>() {
            @Override
            public void onResponse(Call<StarPercentResponse> call, Response<StarPercentResponse> response) {
                Log.d(TAG, "onResponse: code " + response.code());
                StarPercentResponse starPercentResponse = response.body();
                if(response.body() != null){
                    mutableLiveData.setValue(starPercentResponse);
                }
            }

            @Override
            public void onFailure(Call<StarPercentResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
