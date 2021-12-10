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
public class States {
    private final String state;
    private final BigDecimal taxRate;
    
    public States(String state, BigDecimal taxRate){
        this.state = state;
        this.taxRate = taxRate;
    }

    public String getState() {
        return state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }
    
}