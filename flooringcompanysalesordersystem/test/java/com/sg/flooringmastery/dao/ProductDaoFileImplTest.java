/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author ivaylomaslev
 */
public class ProductDaoFileImplTest {
    
    
    private static final String SEED_FILE = "orders_04292020.txt";
    private static final String DATA_FILE = "orders_04292020.txt";
    ProductDao productDao = new ProductDaoFileImpl("products.txt");
     
      @BeforeAll
    public static void setUp() throws IOException{
        Path source = Paths.get(SEED_FILE);
        Path destination = Paths.get(DATA_FILE);
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    public void testGetAll() {
        List<Product> products = productDao.getAll();
        assertTrue(products.size() > 0);
    }

    @Test
    public void testGetProduct() {
        Product cork = productDao.getProduct("Cork");
        assertTrue(cork.getProductType().equals("Cork"));
        assertNotNull(cork.getCostPerSquareFoot());
        assertNotNull(cork.getLaborCostPerSquareFoot());
    }
    
    @Test
    public void testGetProductCaseInsensitive() {
        Product cork = productDao.getProduct("cOrK");
        assertTrue(cork.getProductType().equals("Cork"));
        assertNotNull(cork.getCostPerSquareFoot());
        assertNotNull(cork.getLaborCostPerSquareFoot());
    }
    
    @Test
    
    public void testGetProductNull(){
        Product nullProduct = productDao.getProduct("IS THIS A PRODUCT?");
        assertNull(nullProduct);
    
    }
}
