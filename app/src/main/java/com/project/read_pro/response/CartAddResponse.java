package com.project.read_pro.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.read_pro.model.Cart;

public class CartAddResponse {
    @SerializedName("orderItem")
    @Expose
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
