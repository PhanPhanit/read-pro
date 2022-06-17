package com.project.read_pro.retrofit;

import com.google.gson.JsonObject;
import com.project.read_pro.response.LoginSignupResponse;
import com.project.read_pro.response.ProductResponse;
import com.project.read_pro.response.ReviewResponse;
import com.project.read_pro.response.ShowCurrentUserResponse;
import com.project.read_pro.response.SlideResponse;
import com.project.read_pro.response.StarPercentResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    @GET("wsb-rev")
    Call<ReviewResponse> getReview(@Query("limit") int limit, @Query("page") int page, @Query("product") int productId);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("auth/login")
    Call<LoginSignupResponse> loginUser(@Body JsonObject loginUser);

    @Headers({"Accept: application/json"})
    @DELETE("auth/logout")
    Call<ResponseBody> logoutUser(@Header("authorization") String token);


}
