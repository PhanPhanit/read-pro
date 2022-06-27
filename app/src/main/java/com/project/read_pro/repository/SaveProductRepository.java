package com.project.read_pro.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.read_pro.response.SaveProductResponse;
import com.project.read_pro.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveProductRepository {
    private static final String TAG = SaveProductRepository.class.getSimpleName();

    public LiveData<SaveProductResponse> getSaveProduct(String token){
        final MutableLiveData<SaveProductResponse> mutableLiveData = new MutableLiveData<>();

        RetrofitClient.getInstance().getApi().getSaveProduct(token).enqueue(new Callback<SaveProductResponse>() {
            @Override
            public void onResponse(Call<SaveProductResponse> call, Response<SaveProductResponse> response) {
                Log.d(TAG, "onResponse get: " + response.code());
                if(response.body() != null){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SaveProductResponse> call, Throwable t) {
                Log.d(TAG, "onFailure get: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }
}
