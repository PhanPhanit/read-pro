package com.project.read_pro.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.project.read_pro.repository.SaveProductRepository;
import com.project.read_pro.response.SaveProductAddResponse;
import com.project.read_pro.response.SaveProductResponse;

import okhttp3.ResponseBody;

public class SaveProductViewModel extends ViewModel {
    private final SaveProductRepository saveProductRepository;
    public SaveProductViewModel(){
        saveProductRepository = new SaveProductRepository();
    }

    public LiveData<SaveProductResponse> getSaveProduct(String token){
        return saveProductRepository.getSaveProduct(token);
    }
    public LiveData<SaveProductAddResponse> saveProduct(String token, int productId){
        return saveProductRepository.saveProduct(token, productId);
    }
    public LiveData<ResponseBody> deleteSaveProduct(String token, int id){
        return saveProductRepository.deleteSaveProduct(token, id);
    }
}
