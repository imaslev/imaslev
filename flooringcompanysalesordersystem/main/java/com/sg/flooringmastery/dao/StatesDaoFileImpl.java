/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.States;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ivaylomaslev
 */
public class StatesDaoFileImpl extends FileDao<States> implements StatesDao {

   public StatesDaoFileImpl(String path){
        super(path, 2, true);
    }

    @Override
    public List<States> getAll() {
        try{
            return read(this::mapToTax).stream()
                    .collect(Collectors.toList());
        }catch(StorageException ex){
            return new ArrayList<>();
        }
    }

    @Override
    public States getTaxByState(String state) {
        return getAll().stream()
                .filter(t -> t.getState().equalsIgnoreCase(state))
                .findFirst()
                .orElse(null);
    }
    
    private States mapToTax(String[] tokens){
        String state = tokens[0];
        BigDecimal taxRate = new BigDecimal(tokens[1]);
        return new States(state, taxRate);
    }
    
}
