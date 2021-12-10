/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Selection;
import com.sg.vendingmachine.dto.VendingMachineDetails;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.sg.vendingmachine.dto.Selection;
import com.sg.vendingmachine.dto.VendingMachineDetails;
;

/**
 *
 * @author ivaylomaslev
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
     private Selection firstSelection;
    private Selection secondSelection;
    private List<Selection> selectionList = new ArrayList<>();
    
    public VendingMachineDaoStubImpl() {
        firstSelection = new Selection("Cookie");
        firstSelection.setCost(new BigDecimal ("1.00") );
        firstSelection.setInventory(10);
        
        secondSelection = new Selection("Milk");
        secondSelection.setCost(new BigDecimal ("2.00") );
        secondSelection.setInventory(0);
       
        selectionList.add(firstSelection);
        selectionList.add(secondSelection);
    }

    @Override
    public List<Selection> getAllSelections() throws VendingMachinePersistenceException {
        return selectionList;
    }

    @Override
    public Selection getSelectionByName(String selectionName) throws VendingMachinePersistenceException {
        return firstSelection;
    }
    
    @Override
    public void writeInventory() throws VendingMachinePersistenceException {
        
    }

    @Override
    public VendingMachineDetails getAllVendingMachineDetails() throws VendingMachinePersistenceException {
        VendingMachineDetails empty = new VendingMachineDetails();
        return empty;
    }

    @Override
    public void writeVendingMachineDetails(VendingMachineDetails vmd) throws VendingMachinePersistenceException {
        
    }

    public List<Selection> sortByInput(BigDecimal temp, int alsoTemp) throws VendingMachinePersistenceException {
        return selectionList;
    }

   
    
    
}
