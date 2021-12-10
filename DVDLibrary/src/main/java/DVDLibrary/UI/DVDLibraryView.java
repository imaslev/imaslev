/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDLibrary.UI;

import DVDLibrary.Dto.DVD;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ivaylomaslev
 */
public class DVDLibraryView {

    private UserIO io;
    
    public DVDLibraryView(UserIO io){
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add DVD to Collection. ");
        io.print("2. Remove DVD from Collectio.");
        io.print("3. Edit DVD Information.");
        io.print("4. List All DVD in the Library.");
        io.print("5. Search DVD Library.");
        io.print("6. Exit.");
        return io.readInt("Please select from the above choices. ", 1, 6);

    }

    public DVD getNewDVDInfo() {
        String dvdId = io.readString("Please enter dvd ID");
        String dvdTitle = io.readString("Please enter dvd Title");
        String dvdDirector = io.readString("Please enter dvd Director");
        String dvdGenres = io.readString("Please enter dvd Genres");

        DVD currentDVD = new DVD(dvdId);

        currentDVD.setDVDTitle(dvdTitle);
        currentDVD.setDVDDirector(dvdDirector);
        currentDVD.setGenres(dvdGenres);
        return currentDVD;

    }

    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");

    }

    public void displayDVDSuccessBanner() {
        io.readString("DVD successfully created. Please hit enter to continue.");

    }
     public void editDVD() {
        String dvdId = io.readString("Please enter dvd  new ID");
        String dvdTitle = io.readString("Please enter dvd new  Title");
        String dvdDirector = io.readString("Please enter dvd new  Director");
        String dvdGenres = io.readString("Please enter dvd new Genres");

        DVD currentDVD = new DVD(dvdId);

        currentDVD.setDVDTitle(dvdTitle);
        currentDVD.setDVDDirector(dvdDirector);
        currentDVD.setGenres(dvdGenres);

    }
       public void displaySearchDVDBanner() {
        io.print("=== Enter Title ===");

    }

    

    public void displayDVDList(List<DVD> dvdList) {
        for (DVD currentDVD : dvdList) {
            io.print(currentDVD.getDVDId() + ": " + currentDVD.getDVDTitle() + " " + currentDVD.getDVDDirector());
            {
                io.readString("Please hit enter to continue. ");

            }

        }

    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs");

    }
    
    public void displaiDisplayDVDBanner(){
        io.print("=== Display DVD ===");
        
    }
    
    public String getDVDIdChoice(){
        return io.readString("Please enter the DVD ID");
        
    }
    public void displayDVD(DVD dvd){
        if (dvd != null){
            io.print(dvd.getDVDId());
            io.print(dvd.getDVDTitle() + " " + dvd.getDVDDirector());
            io.print(dvd.getGenres());
            io.print(" ");
        } else{
            io.print("No such dvd.");
            
            
        }
        
        io.readString("Please hit enter to continue.");
    }
    
    public void displayRemoveDVDBanner(){
        io.print("=== DVD Remove ===");
        
    }
    
    public void displayRemoveSuccessBanner(){
        io.readString("DVD successfully remove. Please hit enter to continue.");
        
    }
    
    public void displayExitBanner(){
        io.print("Good Bye!!!");
        
    }
    
    public void displayUnknownCommandBanner(){
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg){
        io.print("=== ERROR ===");
        io.print(errorMsg);
        
    }

    public String getSearchDVDTitle() {
        return io.readString("Search Title");
    }

   
    
}

