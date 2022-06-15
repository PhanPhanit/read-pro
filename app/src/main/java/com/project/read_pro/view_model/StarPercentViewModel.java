package com.project.read_pro.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.project.read_pro.repository.StarPercentRepository;
import com.project.read_pro.response.StarPercentResponse;

public class StarPercentViewModel extends ViewModel {
    private final StarPercentRepository starPercentRepository;

    public StarPercentViewModel(){
        starPercentRepository = new StarPercentRepository();
    }

    public LiveData<StarPercentResponse> getProductStarPercent(int productId){
        return starPercentRepository.getProductStarPercent(productId);
    }

}
