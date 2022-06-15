package com.project.read_pro.retrofit;

import com.project.read_pro.model.User;
import com.project.read_pro.response.ProductResponse;
import com.project.read_pro.response.ReviewResponse;
import com.project.read_pro.response.ShowCurrentUserResponse;
import com.project.read_pro.response.SlideResponse;
import com.project.read_pro.response.StarPercentResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface Api {

    @Headers({"Accept: application/json"})
    @GET("users/showMe")
    Call<ShowCurrentUserResponse> showCurrentUser(@Header("authorization") String token);

    @Headers({"Accept: application/json"})
    @GET("wsb-slide?sort=created_at&limit=10")
    Call<SlideResponse> getAllSlides();

    @Headers({"Accept: application/json"})
    @GET("wsb-pro?limit=8&sort=-created_at")
    Call<ProductResponse> getNewArrival();


    @Headers({"Accept: application/json"})
    @GET("wsb-pro?limit=8&sort=-sold")
    Call<ProductResponse> getBestSelling();

    @Headers({"Accept: application/json"})
    @GET("wsb-pro?limit=10&sort=-views")
    Call<ProductResponse> getRecommendedProduct();

    @Headers({"Accept: application/json"})
    @GET("wsb-rev/star-percent/{productId}")
    Call<StarPercentResponse> getProductStarPercent(@Path("productId") int productId);

    @Headers({"Accept: application/json"})
    @GET("wsb-rev?limit={limit}&page={page}&product={productId}")
    Call<ReviewResponse> getReview(@Path("limit") int limit, @Path("page") int page, @Path("productId") int productId);

}
