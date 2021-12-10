/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import java.math.BigDecimal;
import java.util.List;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Selection;
import com.sg.vendingmachine.dto.VendingMachineDetails;

/**
 *
 * @author ivaylomaslev
 */
public interface VendingMachineService {

    public List<Selection> getAllSelections() throws VendingMachinePersistenceException;

    public Selection getSelectionByName(String itemName) throws VendingMachinePersistenceException;

    public void updateInventory() throws VendingMachinePersistenceException;

    public void purchaseSelection(Selection selection, BigDecimal balance) throws VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException, VendingMachinePersistenceException;

    public VendingMachineDetails getAllVendingMachineDetails() throws VendingMachinePersistenceException;

    public void writeVendingMachineDetails(VendingMachineDetails vmd) throws VendingMachinePersistenceException;

    public void validatePurchase(Selection selection, BigDecimal balance) throws VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException;

    public List<Selection> sortByInput(BigDecimal temp, int alsoTemp) throws VendingMachinePersistenceException;

}
