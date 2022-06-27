package com.project.read_pro.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.project.read_pro.response.CartAddResponse;
import com.project.read_pro.response.CartResponse;
import com.project.read_pro.retrofit.RetrofitClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepository {
    private static final String TAG = CartRepository.class.getSimpleName();

    public LiveData<CartResponse> getProductsInCart(String token){
        final MutableLiveData<CartResponse> mutableLiveData = new MutableLiveData<>();
        RetrofitClient.getInstance().getApi().getProductsInCart(token).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                Log.d(TAG, "onResponse get: " + response.code());
                CartResponse cartResponse = response.body();
                if(response.body() != null){
                    mutableLiveData.setValue(cartResponse);
                }
            }
            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                Log.d(TAG, "onFailure get: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public LiveData<CartAddResponse> addProductToCart(String token, JsonObject body){
        final MutableLiveData<CartAddResponse> mutableLiveData = new MutableLiveData<>();

        RetrofitClient.getInstance().getApi().addProductToCart(token, body).enqueue(new Callback<CartAddResponse>() {
            @Override
            public void onResponse(Call<CartAddResponse> call, Response<CartAddResponse> response) {
                Log.d(TAG, "onResponse create: " + response.code());
                CartAddResponse cartAddResponse = response.body();
                if(cartAddResponse != null){
                    mutableLiveData.setValue(cartAddResponse);
                }
            }

            @Override
            public void onFailure(Call<CartAddResponse> call, Throwable t) {
                Log.d(TAG, "onFailure create: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public LiveData<ResponseBody> clearCart(String token){
        final MutableLiveData<ResponseBody> mutableLiveData = new MutableLiveData<>();

        RetrofitClient.getInstance().getApi().clearCart(token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse clear cart: " + response.code());
                if(response.body() != null){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure clear cart: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public LiveData<CartAddResponse> updateCartQuantity(String token, int id, int quantity){
        final MutableLiveData<CartAddResponse> mutableLiveData = new MutableLiveData<>();
        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("quantity", quantity);
        RetrofitClient.getInstance().getApi().updateCartQuantity(token, id, jsonBody).enqueue(new Callback<CartAddResponse>() {
            @Override
            public void onResponse(Call<CartAddResponse> call, Response<CartAddResponse> response) {
                Log.d(TAG, "onResponse update quantity: " + response.code());
                if(response.body() != null){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CartAddResponse> call, Throwable t) {
                Log.d(TAG, "onFailure update quantity: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public LiveData<ResponseBody> deleteCartItem(String token, int id){
        final MutableLiveData<ResponseBody> mutableLiveData = new MutableLiveData<>();
        RetrofitClient.getInstance().getApi().deleteCartItem(token, id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse delete item: " + response.code());
                if(response.body() != null){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure delete item: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
