/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ivaylomaslev
 */
public class ProductDaoFileImpl extends FileDao<Product> implements ProductDao {

    public ProductDaoFileImpl(String path){
        super(path, 3, true);
    }

    @Override
    public List<Product> getAll() {
        try {
            return read(this::mapToProduct).stream()
                    .collect(Collectors.toList());
        } catch (StorageException ex){
            return new ArrayList<>();
        }
    }

    @Override
    public Product getProduct(String productType) {
        return getAll().stream()
                .filter(p -> p.getProductType().equalsIgnoreCase(productType))
                .findFirst()
                .orElse(null);
    }
    
    private Product mapToProduct(String[] tokens){
        String productType = tokens[0];
        BigDecimal costPerSquareFoot = new BigDecimal(tokens[1]);
        BigDecimal laborCostPerSquareFoot = new BigDecimal(tokens[2]);
        return new Product(productType, costPerSquareFoot, laborCostPerSquareFoot);
    }
}
