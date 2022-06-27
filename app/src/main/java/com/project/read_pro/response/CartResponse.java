package com.project.read_pro.response;

import com.google.gson.annotations.SerializedName;
import com.project.read_pro.model.Cart;

import java.util.List;

public class CartResponse {
    @SerializedName("orderItem")
    private List<Cart> carts;

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}
