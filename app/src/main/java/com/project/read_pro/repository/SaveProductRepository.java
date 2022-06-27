package com.project.read_pro.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.project.read_pro.response.SaveProductAddResponse;
import com.project.read_pro.response.SaveProductResponse;
import com.project.read_pro.retrofit.RetrofitClient;

import okhttp3.ResponseBody;
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

    public LiveData<SaveProductAddResponse> saveProduct(String token, int productId){
        final MutableLiveData<SaveProductAddResponse> mutableLiveData = new MutableLiveData<>();
        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("productId", productId);

        RetrofitClient.getInstance().getApi().saveProduct(token, jsonBody).enqueue(new Callback<SaveProductAddResponse>() {
            @Override
            public void onResponse(Call<SaveProductAddResponse> call, Response<SaveProductAddResponse> response) {
                Log.d(TAG, "onResponse create: " + response.code());
                if(response.body() != null){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SaveProductAddResponse> call, Throwable t) {
                Log.d(TAG, "onFailure create: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }

    public LiveData<ResponseBody> deleteSaveProduct(String token, int id){
        final MutableLiveData<ResponseBody> mutableLiveData = new MutableLiveData<>();

        RetrofitClient.getInstance().getApi().deleteSaveProduct(token, id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse delete: " + response.code());
                if(response.body() != null){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure delete: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }

}
