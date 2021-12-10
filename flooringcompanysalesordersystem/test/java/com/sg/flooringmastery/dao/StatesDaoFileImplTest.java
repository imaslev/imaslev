/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.States;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ivaylomaslev
 */
public class StatesDaoFileImplTest {
    
    StatesDao statesDao = new StatesDaoFileImpl("taxes.txt");

    @Test
    public void testGetAll() {
        List<States> taxes = statesDao.getAll();
        assertTrue(taxes.size() > 0);
    }
    
    @Test
    public void testGetTaxByState() {
        States mnTax = statesDao.getTaxByState("MN");
        assertTrue(mnTax.getState().equals("MN"));
        assertNotNull(mnTax.getTaxRate());
    }
    
    @Test
    public void testGetTaxByStateCaseInsensitive(){
        States mnTax = statesDao.getTaxByState("mN");
        assertTrue(mnTax.getState().equals("MN"));
        assertNotNull(mnTax.getTaxRate());
    }
    
    @Test
    public void testGetTaxByStateNull(){
        States nullTax = statesDao.getTaxByState("Is mayonnaise an instrument?");
        assertNull(nullTax);
    }
    
}
