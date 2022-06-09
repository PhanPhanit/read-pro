package com.project.read_pro.view_model;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.project.read_pro.repository.SlideRepository;
import com.project.read_pro.response.SlideResponse;

public class SlideViewModel extends AndroidViewModel {
    private SlideRepository slideRepository;
    public SlideViewModel(@Nullable Application application){
        super(application);
        slideRepository = new SlideRepository(application);
    }
    public LiveData<SlideResponse> getAllSlides(){
        return slideRepository.getAllSlides();
    }
}
