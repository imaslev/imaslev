/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Selection;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.VendingMachineDetails;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ivaylomaslev
 */
public class VendingMachineController {
    private VendingMachineService service;
    private VendingMachineView view;
    private BigDecimal balance = new BigDecimal("0");
    private VendingMachineDetails vmd;
    
     public VendingMachineController(VendingMachineService service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() throws VendingMachinePersistenceException  {
        
        view.sayHello();
        
        boolean keepGoing = true;
        
        try{
            do {
                /*System.out.println("Before get all 1");*/
                List<Selection> selectionList = service.getAllSelections();
               /* System.out.println("After get all 1");*/
                view.displaySelectionList(selectionList);
              
                int userChoice =  view.getMenuSelection();

                switch(userChoice) {
                    case 1:
                        purchaseItem();
                        break;
                    case 3:
                        sortByInput();
                        break;
                    case 2:
                        keepGoing = false;
                        view.sayGoodBye();
                        break;
                    default:
                        System.out.println("An error has occured!");
                }

            } while(keepGoing);
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
    }
    
   public void purchaseItem() throws VendingMachinePersistenceException{
       
       vmd = service.getAllVendingMachineDetails();
       
       BigDecimal cashInput = view.getMonetaryInput();
       
       balance = balance.add(cashInput);
       
       String temp = view.getSelection();
       
       Selection currentSelection = service.getSelectionByName(temp); 
       
       Change tempChange = new Change(balance,currentSelection.getCost());
       
       try{
           
           service.purchaseSelection(currentSelection, balance);
           
           balance = balance.subtract(currentSelection.getCost());
           
           view.displaySuccesfulPurchase();
           
           view.displayString(tempChange.calculateChange(vmd));
           
           service.writeVendingMachineDetails(vmd);
 
       }catch (VendingMachineInsufficientFundsException | VendingMachineNoItemInventoryException e) {
           view.displayErrorMessage(e.getMessage());
       }
        
   }

    private void sortByInput() throws VendingMachinePersistenceException {
        BigDecimal temp = view.promptUserForCost();
        int alsoTemp = view.promptUserForInventory();
        view.displayString("The list you requested is displayed below ");
        view.displaySelectionList(service.sortByInput(temp, alsoTemp));
        view.displayString(" ");
    }
   
    
}
