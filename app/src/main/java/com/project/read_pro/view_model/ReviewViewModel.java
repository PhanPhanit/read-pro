package com.project.read_pro.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.project.read_pro.repository.ReviewRepository;
import com.project.read_pro.response.ReviewResponse;

public class ReviewViewModel extends ViewModel {
    private final ReviewRepository reviewRepository;
    public ReviewViewModel(){
        reviewRepository = new ReviewRepository();
    }

    public LiveData<ReviewResponse> getReview(int limit, int page, int productId){
        return reviewRepository.getReview(limit, page, productId);
    }
}
