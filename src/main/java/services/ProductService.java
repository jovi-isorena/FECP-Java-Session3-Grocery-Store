package services;

import models.Status;
import java.util.HashMap;

public class ProductService {
    private final HashMap<String, Integer> products = new HashMap<>();

    public Status addProduct(String productName, int quantity){
        productName = productName.trim();
        if( productName.isEmpty()){
            return Status.PRODUCT_NAME_EMPTY;
        }else if(quantity<0){
            return Status.NEGATIVE_QUANTITY;
        }else if(products.containsKey(productName)){
            products.replace(productName, quantity);
            return Status.ADDING_EXISTING_PRODUCT;
        }else{
            products.put(productName, quantity);
            return Status.PRODUCT_ADDED;
        }
    }

    public int checkProduct(String productName) {
        productName = productName.trim();
        if (productName.isEmpty()) {
            return -1;
        } else {
            return products.getOrDefault(productName, -1);
        }
    }

    public Status updateProduct(String productName, int newQuantity){
        productName = productName.trim();
        if(!products.containsKey(productName)){
            return Status.PRODUCT_DOES_NOT_EXISTS;
        }else if(newQuantity<0){
            return Status.NEGATIVE_QUANTITY;
        }else {
            products.put(productName, newQuantity);
            return Status.PRODUCT_UPDATED;
        }
    }

    public Status removeProduct(String productName){
        productName = productName.trim();
        if(!products.containsKey(productName)) {
            return Status.PRODUCT_DOES_NOT_EXISTS;
        }else{
            products.remove(productName);
            return Status.PRODUCT_REMOVED;
        }
    }

    public HashMap<String, Integer> getProducts(){
        return new HashMap<>(products);
    }
}
