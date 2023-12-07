package com.example.lam_44549_43431_shopping_cart;

public class Product {
    private int id;
    private String description;
    private int quantity;
    private int bought;

    public static int idNum=0;

    public Product(){}
    public Product(String description, int quantity, int bought) {
        this.description = description;
        this.quantity = quantity;
        this.bought = bought;
    }
    public Product(String description){
        this.description=description;
        quantity=0;
        bought=0;
        id=idNum++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getBought() {
        boolean bool;
        bool = bought != 0;
        return bool;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }
}
