/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.util.List;

/**
 *
 * @author ivaylomaslev
 */
public interface OrderDao {
    
    public List<Order> getAll();
    
    public Order addOrder(Order order) throws StorageException;
    
    public Order getOrder(int orderId);
    
    public boolean editOrder(Order order) throws StorageException;
    
    public boolean removeOrder(Order order) throws StorageException;

    public void exportAllOrders(Order order) throws StorageException;
    
    public void addHeader() throws StorageException;
    
    
}
