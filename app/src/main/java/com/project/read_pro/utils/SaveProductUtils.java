package com.project.read_pro.utils;

import com.project.read_pro.model.SaveProduct;

import java.util.ArrayList;
import java.util.List;

public class SaveProductUtils {
    private static SaveProductUtils instance;
    private SaveProductUtils(){}
    private List<SaveProduct> saveProducts = new ArrayList<>();

    public static SaveProductUtils getInstance(){
        if(instance == null){
            instance = new SaveProductUtils();
        }
        return instance;
    }

    public List<SaveProduct> getSaveProducts() {
        return saveProducts;
    }

    public void setSaveProducts(List<SaveProduct> saveProducts) {
        this.saveProducts = saveProducts;
    }
}
