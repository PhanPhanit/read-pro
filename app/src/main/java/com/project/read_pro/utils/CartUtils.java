package com.project.read_pro.utils;

import com.project.read_pro.model.Cart;
import com.project.read_pro.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartUtils {
    private static CartUtils instance;
    private CartUtils(){}
    private List<Cart> carts = new ArrayList<>();
    public static CartUtils getInstance(){
        if(instance == null){
            instance = new CartUtils();
        }
        return instance;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public void addToCart(Cart cart){
        boolean inCart = false;
        for(int i=0;i<carts.size();i++){
            Cart cartItem = carts.get(i);
            if(cartItem.getId() == cart.getId()){
                carts.get(i).setQuantity(cart.getQuantity());
                inCart = true;
            }
        }
        if(!inCart){
            carts.add(cart);
        }
    }

    public int countProductsInCart(){
        int count = 0;
        for(Cart item : carts){
            count += item.getQuantity();
        }
        return count;
    }

    public void clearProductInCart(){
        carts.clear();
    }
    public void updateProductQuantity(int cartId, int quantity){
        for(int i=0;i<carts.size();i++){
            if(carts.get(i).getId() == cartId){
                carts.get(i).setQuantity(quantity);
                break;
            }
        }
    }

    public void deleteCartItem(int cartId){
        for(int i=0;i<carts.size();i++){
            if(carts.get(i).getId() == cartId){
                carts.remove(i);
                break;
            }
        }
    }

    public double getTotalPrice(){
        double totalPrice = 0;
        for(Cart cart : carts){
            Product product = cart.getProduct();
            double price = product.getPrice();
            double discount = product.getDiscount();
            totalPrice += (price - discount) * cart.getQuantity();
        }
        return totalPrice;
    }

}
