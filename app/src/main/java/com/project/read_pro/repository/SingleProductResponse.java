package com.project.read_pro.repository;

import com.project.read_pro.model.Product;

public class SingleProductResponse {
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
