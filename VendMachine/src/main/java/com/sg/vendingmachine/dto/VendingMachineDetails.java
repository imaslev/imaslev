/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

/**
 *
 * @author ivaylomaslev
 */
public class VendingMachineDetails {
     int[] intArray = new int[5];
    
   
    public VendingMachineDetails() {
        
    }
    
    public void setIntArray(int[] intArray) {
        this.intArray = intArray;
    }
    
    public int[] getIntArray() {
        return this.intArray;
    }
    
}
