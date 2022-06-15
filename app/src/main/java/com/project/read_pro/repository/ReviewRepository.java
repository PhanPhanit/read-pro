package com.project.read_pro.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.read_pro.response.ReviewResponse;
import com.project.read_pro.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewRepository {
    private static final String TAG = ReviewRepository.class.getSimpleName();

    public LiveData<ReviewResponse> getReview(int limit, int page, int productid){
        final MutableLiveData<ReviewResponse> mutableLiveData = new MutableLiveData<>();
        RetrofitClient.getInstance().getApi().getReview(limit, page, productid).enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if(response.body() != null){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
