package com.project.read_pro.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.project.read_pro.repository.ProductRepository;
import com.project.read_pro.repository.SingleProductResponse;
import com.project.read_pro.response.ProductResponse;

public class ProductViewModel extends ViewModel {
    private final ProductRepository productRepository;

    public ProductViewModel(){
        productRepository = new ProductRepository();
    }

    public LiveData<ProductResponse> getNewArrival(){
        return productRepository.getNewArrival();
    }
    public LiveData<ProductResponse> getBestSelling(){
        return productRepository.getBestSelling();
    }
    public LiveData<ProductResponse> getRecommendedProduct(){
        return productRepository.getRecommendedProduct();
    }
    public LiveData<SingleProductResponse> getSingleProduct(int id){
        return productRepository.getSingleProduct(id);
    }
}
