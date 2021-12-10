/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

/**
 *
 * @author ivaylomaslev
 */
public class Product {
 
    private String description;
    private double price;

    public Product(String description, double price) {
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return description + "\t" + price;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
    

