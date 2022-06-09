package com.project.read_pro.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.read_pro.response.SlideResponse;
import com.project.read_pro.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideRepository {
    private static final String TAG = SlideRepository.class.getSimpleName();
    private Application application;

    public SlideRepository(Application application){
        this.application = application;
    }

    public LiveData<SlideResponse> getAllSlides(){
        final MutableLiveData<SlideResponse> mutableLiveData = new MutableLiveData<>();
        RetrofitClient.getInstance().getApi().getAllSlides().enqueue(new Callback<SlideResponse>() {
            @Override
            public void onResponse(Call<SlideResponse> call, Response<SlideResponse> response) {
                Log.d(TAG, String.valueOf(response.code()));
                if(response.body() != null){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SlideResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }


}
