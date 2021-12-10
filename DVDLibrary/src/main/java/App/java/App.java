/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.java;

import DVDLibrary.UI.DVDLibraryConsoleImpl;
import DVDLibrary.UI.DVDLibraryView;
import DVDLibrary.UI.UserIO;
import DVDLibrary.controller.DVDLibraryController;
import DVDLibrary.dao.DVDLibraryDao;
import DVDLibrary.dao.DVDLibraryDaoFileImpl;

/**
 *
 * @author ivaylomaslev
 */
public class App {
     public static void main(String[] args) {
        
     UserIO myIo = new DVDLibraryConsoleImpl();   
     DVDLibraryView myView = new DVDLibraryView(myIo);
     DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
     DVDLibraryController controller = new DVDLibraryController(myDao, myView);
     controller.run();
      
                
        }

   

    
}
