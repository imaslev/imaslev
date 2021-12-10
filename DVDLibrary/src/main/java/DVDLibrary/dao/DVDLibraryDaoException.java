/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDLibrary.dao;

import DVDLibrary.Dto.DVD;
/**
 *
 * @author ivaylomaslev
 */
public class DVDLibraryDaoException extends Exception {
    
     public DVDLibraryDaoException(String message){
    
    super(message);
}
    
    public DVDLibraryDaoException(String message,Throwable cause){
        super(message, cause);
    }
    
    
   
    }
