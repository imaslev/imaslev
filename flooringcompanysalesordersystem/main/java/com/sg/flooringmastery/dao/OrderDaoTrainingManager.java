/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;

/**
 *
 * @author ivaylomaslev
 */
public class OrderDaoTrainingManager extends OrderDaoManagerFileImpl {
    
    @Override
    public Order add(Order order) {
        return null;
    }
    
    @Override
    public boolean editOrder(Order orderToEdit){
        return false;
    }
    
    @Override
    public boolean removeOrder(Order orderToRemove) {
        return false;
    }
}
