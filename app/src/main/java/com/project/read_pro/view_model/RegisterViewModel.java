package com.project.read_pro.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.project.read_pro.repository.RegisterRepository;
import com.project.read_pro.response.LoginSignupResponse;

public class RegisterViewModel extends ViewModel {
    private final RegisterRepository registerRepository;

    public RegisterViewModel(){
        registerRepository = new RegisterRepository();
    }

    public LiveData<LoginSignupResponse> registerUser(String name, String email, String password){
        return registerRepository.registerUser(name, email, password);
    }
}
