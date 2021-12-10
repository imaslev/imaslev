/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ivaylomaslev
 */
public class OrderDaoManagerFileImplTest {
    
    private static final String SEED_FILE = "orders_04292020.txt";
    private static final String DATA_FILE = "orders_04292020.txt";
    private static final LocalDate FILE_DATE = LocalDate.of(2020, 4, 29);
    private OrderDaoManager orderDaoManager = new OrderDaoManagerFileImpl();
    
    @BeforeAll
    public static void setUp() throws IOException{
        Path source = Paths.get(SEED_FILE);
        Path destination = Paths.get(DATA_FILE);
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
    }
    
    @Test
    public void testAddOrder(){
        Order addOrder = new Order(FILE_DATE);
        int initialSize = orderDaoManager.getOrdersByDate(FILE_DATE).size();
        addOrder.setOrderId(8);
        addOrder.setCustomerName("Ivo M.");
        addOrder.setState("WI");
        addOrder.setProductType("Glass Tile");
        addOrder.setArea(BigDecimal.TEN);
        addOrder.setTaxRate(new BigDecimal("5.00"));
        addOrder.setCostPerSquareFoot(BigDecimal.ZERO);
        addOrder.setLaborCostPerSquareFoot(BigDecimal.ZERO);
        addOrder.setMaterialCost(BigDecimal.ZERO);
        addOrder.setLaborCost(BigDecimal.ZERO);
        addOrder.setTaxCost(BigDecimal.ZERO);
        addOrder.setTotalCost(BigDecimal.ZERO);
        
        orderDaoManager.add(addOrder);
        assertEquals(orderDaoManager.getOrdersByDate(FILE_DATE).size(), initialSize + 1);
    
    }

    @Test
    public void testGetOrdersByDate() {
        List<Order> ordersByDate = orderDaoManager.getOrdersByDate(FILE_DATE);
        assertTrue(ordersByDate.size() > 0);
    }

    @Test
    public void testGeneratePath() {
        LocalDate date = LocalDate.of(2020, 4, 29);
        String expectedPath = "orders_04292020.txt";
        System.out.println(orderDaoManager.generatePath(date));
        assertTrue(orderDaoManager.generatePath(date).equals(expectedPath));
    }
    
    @Test
    public void testGetOrderByDateByList(){
        Order addOrder = new Order(FILE_DATE);
        int initialSize = orderDaoManager.getOrdersByDate(FILE_DATE).size();
        addOrder.setOrderId(7);
        addOrder.setCustomerName("Eva M.");
        addOrder.setState("FL");
        addOrder.setProductType("Ceramic Tile");
        addOrder.setArea(BigDecimal.TEN);
        addOrder.setTaxRate(new BigDecimal("4.00"));
        addOrder.setCostPerSquareFoot(BigDecimal.ZERO);
        addOrder.setLaborCostPerSquareFoot(BigDecimal.ZERO);
        addOrder.setMaterialCost(BigDecimal.ZERO);
        addOrder.setLaborCost(BigDecimal.ZERO);
        addOrder.setTaxCost(BigDecimal.ZERO);
        addOrder.setTotalCost(BigDecimal.ZERO);
        orderDaoManager.add(addOrder);
        List<Order> ordersByDate = orderDaoManager.getOrdersByDate(FILE_DATE);
         //I want to get the last one added to the list!
        Order order = ordersByDate.get(initialSize);
        assertTrue(order.getDate().equals(addOrder.getDate()));
        assertTrue(order.getCustomerName().equals(addOrder.getCustomerName()));
        assertTrue(order.getState().equals(addOrder.getState()));
        assertTrue(order.getTotalCost().equals(addOrder.getTotalCost()));
    }
    
    @Test
    public void testEditOrder(){
        Order edits = new Order(FILE_DATE);
        int initialSize = orderDaoManager.getOrdersByDate(FILE_DATE).size();
        edits.setOrderId(8);
        edits.setCustomerName("Maslev Co.");
        edits.setState("WI");
        edits.setProductType("Glass Tile");
        edits.setArea(BigDecimal.TEN);
        edits.setTaxRate(new BigDecimal("5.00"));
        edits.setCostPerSquareFoot(BigDecimal.ZERO);
        edits.setLaborCostPerSquareFoot(BigDecimal.ZERO);
        edits.setMaterialCost(BigDecimal.ZERO);
        edits.setLaborCost(BigDecimal.ZERO);
        edits.setTaxCost(BigDecimal.ZERO);
        edits.setTotalCost(BigDecimal.ZERO);
        
        orderDaoManager.editOrder(edits);
        assertEquals(orderDaoManager.getOrdersByDate(FILE_DATE).size(), initialSize);
    }
    
}
