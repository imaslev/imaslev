/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author ivaylomaslev
 */
public class Selection {
     private final  String selectionName;
    private BigDecimal cost;
    private int inventory; 
    
    public Selection(String name) {
        this.selectionName = name;
    }

    public String getSelectionName() {
        return selectionName;
    }

    public BigDecimal getCost() {
        return cost;
    } 

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
        }
    
    
}
