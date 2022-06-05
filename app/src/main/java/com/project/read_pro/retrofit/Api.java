package com.project.read_pro.retrofit;

import com.project.read_pro.response.SlideResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {


    @GET("wsb-slide?sort=created_at&limit=10")
    Call<SlideResponse> getAllSlides();



}
