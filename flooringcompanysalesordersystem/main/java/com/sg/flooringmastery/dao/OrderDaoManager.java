/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ivaylomaslev
 */
public interface OrderDaoManager {
    
     public String generatePath(LocalDate dateSelection);
    
    public OrderDao buildDao(LocalDate dateSelection);
    
    public List<Order> getOrdersByDate(LocalDate dateSelection);
    
    public Order add(Order order);
    
    public boolean editOrder(Order orderToEdit);
    
    public boolean removeOrder(Order orderToRemove);
    
    public Order getOrder(Order requestedOrder);
    
    
}
