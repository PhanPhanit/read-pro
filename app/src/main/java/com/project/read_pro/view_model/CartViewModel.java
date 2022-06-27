package com.project.read_pro.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;
import com.project.read_pro.repository.CartRepository;
import com.project.read_pro.response.CartAddResponse;
import com.project.read_pro.response.CartResponse;

import okhttp3.ResponseBody;

public class CartViewModel extends ViewModel {
    private final CartRepository cartRepository;
    public CartViewModel(){
        cartRepository = new CartRepository();
    }
    public LiveData<CartResponse> getProductsInCart(String token){
        return cartRepository.getProductsInCart(token);
    }
    public LiveData<CartAddResponse> addProductToCart(String token, JsonObject body){
        return cartRepository.addProductToCart(token, body);
    }
    public LiveData<ResponseBody> clearCart(String token){
        return cartRepository.clearCart(token);
    }
    public LiveData<CartAddResponse> updateCartQuantity(String token, int id, int quantity){
        return cartRepository.updateCartQuantity(token, id, quantity);
    }
    public LiveData<ResponseBody> deleteCartItem(String token, int id){
        return cartRepository.deleteCartItem(token, id);
    }
}
