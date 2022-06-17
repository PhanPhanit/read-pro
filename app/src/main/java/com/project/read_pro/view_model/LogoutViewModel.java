package com.project.read_pro.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.project.read_pro.repository.LogoutRepository;

import okhttp3.ResponseBody;

public class LogoutViewModel extends ViewModel {
    private final LogoutRepository logoutRepository;

    public LogoutViewModel(){
        logoutRepository = new LogoutRepository();
    }

    public LiveData<ResponseBody> logoutUser(String token){
        return logoutRepository.logoutUser(token);
    }
}
