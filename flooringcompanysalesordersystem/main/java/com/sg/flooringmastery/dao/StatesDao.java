/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.States;
import java.util.List;

/**
 *
 * @author ivaylomaslev
 */
public interface StatesDao {
    
     public List<States> getAll();
    
    public States getTaxByState(String state);
    
}
