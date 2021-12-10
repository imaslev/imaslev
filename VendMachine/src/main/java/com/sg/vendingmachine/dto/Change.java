/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import com.sg.vendingmachine.dto.VendingMachineDetails;

/**
 *
 * @author ivaylomaslev
 */
public class Change {
    private BigDecimal change;
    private final BigDecimal input;
    private final BigDecimal cost;
    private final BigDecimal changeBeforeBreakDown;
    private int tempHolder;
    
    public Change(BigDecimal input, BigDecimal cost) {
        this.input = input;
        this.cost = cost;
        this.changeBeforeBreakDown = input.subtract(cost);
    }
    
    public String calculateChange(VendingMachineDetails vmd) {
        String tempString = "";
        change  = input.subtract(cost);
        int[] intArray = vmd.getIntArray();
        Denominations[] denomArr = Denominations.values();
        
      for (int i = 0; i  < intArray.length ; i++)/*for (int i = 0; i  < 6 ; i++){*/{
           /* System.out.println("Change1 i = " + i);
            System.out.println("DenomArray[i] = " + denomArr[i]);
              System.out.println("intArray[i] = " + intArray[i]);*/
            tempString += calculateAmount(denomArr[i], intArray[i]);
          /*  System.out.println(" Change "+ i + " " + tempString);*/
            intArray[i] = tempHolder; 
        }
        
        vmd.setIntArray(intArray);
        
        return "You're change in denominations and quantity is displayed below \n" + changeBeforeBreakDown + "\n" +  tempString;
    } 
    
    public String calculateAmount(Denominations den,int amountInmachine){
        int tempQuant = 0;
        System.out.println("calculateAmount den = " + den);
        System.out.println("calculateAmount amountInmachine " + amountInmachine);
        while(change.compareTo(den.description) >=0 && amountInmachine  > 0){
            tempQuant++;
            amountInmachine--;
            change = change.subtract(den.description);
        }
        
        tempHolder = amountInmachine;
        return den + " :  " + tempQuant + "\n";
        
    }     
    
}
