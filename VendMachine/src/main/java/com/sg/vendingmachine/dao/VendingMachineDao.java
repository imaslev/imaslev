/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Selection;
import com.sg.vendingmachine.dto.VendingMachineDetails;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ivaylomaslev
 */
public interface VendingMachineDao {

    public List<Selection> getAllSelections() throws VendingMachinePersistenceException;

    public Selection getSelectionByName(String selectionName) throws VendingMachinePersistenceException;

    public void writeInventory() throws VendingMachinePersistenceException;

    public VendingMachineDetails getAllVendingMachineDetails() throws VendingMachinePersistenceException;

    public void writeVendingMachineDetails(VendingMachineDetails vmd) throws VendingMachinePersistenceException;

    public List<Selection> sortByInput(BigDecimal temp, int alsoTemp) throws VendingMachinePersistenceException;

}
