package com.project.read_pro.utils;

import com.project.read_pro.model.Product;
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

    public void clearSaveProduct(){
        saveProducts.clear();
    }
    public boolean isProductSaved(Product product){
        boolean isSaved = false;
        for(SaveProduct item : saveProducts){
            if(item.getProductId() == product.getId()){
                isSaved = true;
                break;
            }
        }
        return isSaved;
    }
    public void addSaveProduct(SaveProduct saveProduct){
        saveProducts.add(saveProduct);
    }

    public int getSaveProductId(Product product){
        int saveProductId = 0;
        for(SaveProduct saveProduct : saveProducts){
            if(saveProduct.getProductId() == product.getId()){
                saveProductId = saveProduct.getId();
                break;
            }
        }
        return saveProductId;
    }

    public void removeSaveProduct(int saveProductId){
        for(int i=0;i<saveProducts.size();i++){
            if(saveProducts.get(i).getId() == saveProductId){
                saveProducts.remove(i);
                break;
            }
        }
    }
}
