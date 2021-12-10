/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

/**
 *
 * @author ivaylomaslev
 */
public class StorageException extends Exception {
    
    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable innerEx) {
        super(message, innerEx);
    }
    
}
