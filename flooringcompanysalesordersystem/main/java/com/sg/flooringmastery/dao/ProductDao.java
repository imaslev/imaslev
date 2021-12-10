/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.util.List;

/**
 *
 * @author ivaylomaslev
 */
public interface ProductDao {
    
    public List<Product> getAll();
    
    public Product getProduct(String productType);

    
    
}
