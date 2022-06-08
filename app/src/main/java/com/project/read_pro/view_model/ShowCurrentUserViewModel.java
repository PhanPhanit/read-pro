package com.project.read_pro.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.project.read_pro.repository.ShowCurrentUserRepository;
import com.project.read_pro.response.ShowCurrentUserResponse;

public class ShowCurrentUserViewModel extends ViewModel {
    private final ShowCurrentUserRepository showCurrentUserRepository;
    public ShowCurrentUserViewModel(){
        showCurrentUserRepository = new ShowCurrentUserRepository();
    }

    public LiveData<ShowCurrentUserResponse> ShowCurrentUser(String token){
        return showCurrentUserRepository.showCurrentUser(token);
    }
}
