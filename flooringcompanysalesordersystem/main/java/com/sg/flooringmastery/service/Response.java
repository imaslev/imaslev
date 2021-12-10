/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Order;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivaylomaslev
 */
public class Response {
    
    private final ArrayList<String> errorMessages = new ArrayList<>();
    private Order order;

    public boolean hasError() {
        return errorMessages.size() > 0;
    }

    public void addError(String message) {
        errorMessages.add(message);
    }

    public List<String> getErrors() {
        return new ArrayList<>(errorMessages);
    }

    public Order getOrder() {
        return order;
    
    }
    
     public void setOrder(Order order){
        this.order = order;
    }
    
    public void writeAll(Order order){
        this.order = order;
    } 
}