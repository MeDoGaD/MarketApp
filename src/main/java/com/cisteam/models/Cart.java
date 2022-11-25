package com.cisteam.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
public static Cart myCart;

static {
    myCart=new Cart();
}
    private List<Product>products;
    private double total_Price;

   public static Cart getInstance(){
       return myCart;
   }
    public Cart() {
        this.products=new ArrayList<>();
        this.total_Price=0;
    }

    public double getTotal_Price() {
        return total_Price;
    }

    public void setTotal_Price(double total_Price) {
        this.total_Price = total_Price;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public void addProductToCart(Product product){
        products.add(product);
    }
    public void removeProductFromCart(int id){
        for(int i=0;i<products.size();i++){
            if(products.get(i).getId()==id)
            {
                DecrementTotalBy(products.get(i).getQuantity()*products.get(i).getPrice());
                products.remove(i);
                return;
            }
        }
    }
    public void IncrementTotalBy(double val){
       total_Price+=val;
    }
    public void DecrementTotalBy(double val){
        total_Price-=val;
    }

    public void clearCart(){
       this.total_Price=0;
       this.products.clear();
    }
}
