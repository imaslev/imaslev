/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

/**
 *
 * @author ivaylomaslev
 */
public class ViewTraining extends View{
    
    public ViewTraining(UserIO io){
        super(io);
    }
    
    @Override
    public void displayWriteToFileError(){
        io.print("Changes not saved to file. You are currently in Training Mode.");
        io.print("Switch xml configuation file in App.java to switch to Prod mode.");
    }
    
    
    
}
