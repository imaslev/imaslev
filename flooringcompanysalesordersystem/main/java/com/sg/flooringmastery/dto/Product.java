/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author ivaylomaslev
 */
public class Product {
    private final String productType;
    private final BigDecimal costPerSquareFoot;
    private final BigDecimal laborCostPerSquareFoot;
    
    public Product(String type, BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot){
        this.productType = type;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }
    
    
}