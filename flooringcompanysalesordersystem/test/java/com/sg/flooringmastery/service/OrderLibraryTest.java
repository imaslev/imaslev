/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Order;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ivaylomaslev
 */
public class OrderLibraryTest {
    
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContextTest.xml");
    private static final String SEED_FILE = "orders_04292020.txt";
    private static final String DATA_FILE = "orders_04292020.txt";
    OrderLibrary orderLibrary = ctx.getBean("orderLibrary", OrderLibrary.class);
    private static final LocalDate FILE_DATE = LocalDate.of(2020, 4, 29);
   
    @BeforeAll
    public static void setUp() throws IOException{
        Path source = Paths.get(SEED_FILE);
        Path destination = Paths.get(DATA_FILE);
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
    }


    @Test
    public void testValidateOrderValid() {
        Order order = new Order(FILE_DATE);
        order.setCustomerName("Test Business");
        order.setProductType("Wood");
        order.setState("NE");
        order.setArea(BigDecimal.TEN);
        
        Response response = orderLibrary.validateOrder(order);
        assertFalse(response.hasError());
    }
    
    @Test
    public void testValidateOrderOneError() {
        Order order = new Order(FILE_DATE);
        order.setCustomerName("Test");
        order.setProductType("Ceramic Tile");
        order.setState("WA");
        order.setArea(BigDecimal.TEN);
        
        Response response = orderLibrary.validateOrder(order);
        assertEquals(response.getErrors().size(), 2);
    }
    
    @Test
    public void testValidateOrderMultipleErrors() {
        Order order = new Order(FILE_DATE);
        order.setCustomerName(" ");
        order.setProductType("Floor");
        order.setState("WA");
        order.setArea(BigDecimal.TEN);

        Response response = orderLibrary.validateOrder(order);
        assertEquals(response.getErrors().size(), 3);
    }

    @Test
    public void testIsNullOrWhiteSpaceNull() {
        String nullString = null;
        assertTrue(orderLibrary.isNullOrWhiteSpace(nullString));
    }
    
    @Test
    public void testIsNullOrWhiteSpaceWhiteSpace(){
        String whiteSpaceString = "    ";
        assertTrue(orderLibrary.isNullOrWhiteSpace(whiteSpaceString));
    }
    
    @Test
    public void testIsNullOrWhiteSpaceText(){
        String validString = "Flooring Company";
        assertFalse(orderLibrary.isNullOrWhiteSpace(validString));
    }

    @Test
    public void testIsUnsupportedStateUnsupported() {
        String unsupported = "WA";
        assertTrue(orderLibrary.isUnsupportedState(unsupported));
    }
    
    @Test
    public void testIsUnsupportedStateSupported() {
        String supported = "MN";
        assertFalse(orderLibrary.isUnsupportedState(supported));
    }

    @Test
    public void testIsUnsupportedProductTypeUnsupported() {
        String unsupported = "flooring";
        assertTrue(orderLibrary.isUnsupportedProductType(unsupported));
    }
    
    @Test
    public void testIsUnsupportedProductTypeSupported() {
        String supported = "Slate";
        assertFalse(orderLibrary.isUnsupportedProductType(supported));
    }
    
    @Test
    public void testIsUnsupportedProductTypeSupportedCaseInsensitive() {
        String supported = "Carpet";
        assertFalse(orderLibrary.isUnsupportedProductType(supported));
    }

    @Test
    public void testCalculateCostsReadingValues() {
        Order order = new Order(FILE_DATE);
        order.setCustomerName("Test");
        order.setProductType("Wood");
        order.setState("MN");
        order.setArea(BigDecimal.TEN);
        
        order = orderLibrary.calculateCosts(order);
        
        assertTrue(order.getTaxRate().equals(new BigDecimal("6.75")));
        assertTrue(order.getCostPerSquareFoot().equals(new BigDecimal("3.00")));
        assertTrue(order.getLaborCostPerSquareFoot().equals(new BigDecimal("4.25")));
    }
    
    @Test
    public void testCalculateCostsCalculations() {
        Order order = new Order(FILE_DATE);
        order.setCustomerName(" ");
        order.setProductType("Carpet");
        order.setState("OH");
        order.setArea(BigDecimal.TEN);
        
        order = orderLibrary.calculateCosts(order);

        assertTrue(order.getTaxCost().equals(new BigDecimal("1.81")));
        assertTrue(order.getTotalCost().equals(new BigDecimal("74.31")));
    }
}
