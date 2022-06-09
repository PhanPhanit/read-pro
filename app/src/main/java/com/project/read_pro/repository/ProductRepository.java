package com.project.read_pro.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.read_pro.response.ProductResponse;
import com.project.read_pro.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    private static final String TAG = ProductRepository.class.getSimpleName();

    public LiveData<ProductResponse> getNewArrival(){
        final MutableLiveData<ProductResponse> mutableLiveData = new MutableLiveData<>();

        RetrofitClient.getInstance().getApi().getNewArrival().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                Log.d(TAG, "onResponse: Succeeded get new arrival");
                if(response.body() != null){
                    mutableLiveData.setValue(response.body());
                    Log.d(TAG, String.valueOf(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage() + " get best selling");
            }
        });
        return mutableLiveData;
    }


    public LiveData<ProductResponse> getBestSelling(){
        final MutableLiveData<ProductResponse> mutableLiveData = new MutableLiveData<>();
        RetrofitClient.getInstance().getApi().getBestSelling().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                Log.d(TAG, "onResponse: Succeeded get best selling");
                if(response.body() != null){
                    mutableLiveData.setValue(response.body());
                    Log.d(TAG, String.valueOf(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage() + " get best selling");
            }
        });
        return mutableLiveData;
    }


    public LiveData<ProductResponse> getRecommendedProduct(){
        final MutableLiveData<ProductResponse> mutableLiveData = new MutableLiveData<>();

        RetrofitClient.getInstance().getApi().getRecommendedProduct().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                Log.d(TAG, "onResponse: Succeeded get recommended product");
                if(response.body() != null){
                    mutableLiveData.setValue(response.body());
                    Log.d(TAG, String.valueOf(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage() + " get recommended product");
            }
        });

        return mutableLiveData;
    }

}
