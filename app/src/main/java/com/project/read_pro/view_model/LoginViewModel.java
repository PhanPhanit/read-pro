package com.project.read_pro.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.project.read_pro.repository.LoginRepository;
import com.project.read_pro.response.LoginSignupResponse;

public class LoginViewModel extends ViewModel {
    private final LoginRepository loginRepository;

    public LoginViewModel(){
        loginRepository = new LoginRepository();
    }

    public LiveData<LoginSignupResponse> getLoginResponseLiveData(String email, String password){
        return loginRepository.getLoginResponseData(email, password);
    }
}
