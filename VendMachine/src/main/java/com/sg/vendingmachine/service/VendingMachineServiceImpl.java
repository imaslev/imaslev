/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import java.math.BigDecimal;
import java.util.List;
import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Selection;
import com.sg.vendingmachine.dto.VendingMachineDetails;

/**
 *
 * @author ivaylomaslev
 */
public class VendingMachineServiceImpl implements VendingMachineService {
    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    

    public VendingMachineServiceImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;  
        this.auditDao = auditDao;
    }

    @Override
    public List<Selection> getAllSelections() throws VendingMachinePersistenceException {
        return dao.getAllSelections();
    }
    
    @Override
    public VendingMachineDetails getAllVendingMachineDetails() throws VendingMachinePersistenceException {
        return dao.getAllVendingMachineDetails();
    }
    
    @Override
    public void writeVendingMachineDetails(VendingMachineDetails vmd) throws VendingMachinePersistenceException {
        dao.writeVendingMachineDetails(vmd);
    }

    @Override
    public Selection getSelectionByName(String selectionName) throws VendingMachinePersistenceException {
        return dao.getSelectionByName(selectionName);
    }
    
    @Override
    public void updateInventory() throws VendingMachinePersistenceException {
        dao.writeInventory();
    }
    
   
    public void logTransaction(String transaction) throws VendingMachinePersistenceException{
         auditDao.writeAuditEntry(transaction);   
    }

    @Override
    public void purchaseSelection(Selection selection, BigDecimal balance) throws VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException, VendingMachinePersistenceException  {
        
        validatePurchase(selection, balance);
        
        selection.setInventory(selection.getInventory() - 1);
        
        try{
           updateInventory(); 
        } catch (VendingMachinePersistenceException e){
            throw new VendingMachinePersistenceException ("Unable to update inventory", e);
        }
        
    }
    
    public void validatePurchase(Selection selection, BigDecimal balance) throws VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException {

        if (selection.getCost().doubleValue() > balance.doubleValue() ){
          
            throw new VendingMachineInsufficientFundsException(
                    "ERROR: Insufficient Funds.");
        }
        
        if (selection.getInventory() == 0 ){
          
            throw new VendingMachineNoItemInventoryException(
                    "ERROR: Item not available");
        }
    }

    @Override
    public List<Selection> sortByInput(BigDecimal temp, int alsoTemp) throws VendingMachinePersistenceException {
        return dao.sortByInput(temp, alsoTemp);
    }
    
    
}
