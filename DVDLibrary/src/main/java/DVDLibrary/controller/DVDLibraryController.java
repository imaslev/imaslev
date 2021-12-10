/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDLibrary.controller;

import DVDLibrary.Dto.DVD;
import DVDLibrary.UI.DVDLibraryConsoleImpl;
import DVDLibrary.UI.DVDLibraryView;
import DVDLibrary.UI.UserIO;
import DVDLibrary.dao.DVDLibraryDao;
import java.util.List;
import DVDLibrary.dao.DVDLibraryDaoException;
import DVDLibrary.dao.DVDLibraryDaoFileImpl;

/**
 *
 * @author ivaylomaslev
 */
public class DVDLibraryController {

    DVDLibraryDao dao;
    DVDLibraryView view;

    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

    private UserIO io = new DVDLibraryConsoleImpl();

    /*
    Allow the user to add a DVD to the collection
Allow the user to remove a DVD from the collection
Allow the user to edit the information for an existing DVD in the collection
Allow the user to list the DVDs in the collection
Allow the user to display the information for a particular DVD
Allow the user to search for a DVD by title
Load the DVD library from a file
Save the DVD library back to the file when the program completes
Allows the user to add, edit, or delete many DVDs in one session
     */
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {

            while (keepGoing) {
                /* io.print("Main Menu");
            io.print("1. Add DVD to Collection. ");
            io.print("2. Remove DVD from Collectio.");
            io.print("3. Edit DVD Information.");
            io.print("4. Exit.");*/

                menuSelection = getMenuSelection();
               /* System.out.println("menu selection " + menuSelection);*/
                switch (menuSelection) {

                    case 1:
                        createDVD();
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        editDVD();
                        break;
                    case 4:
                        listDVDs();
                        break;
                    case 5:
                       /* System.out.println("m2");*/
                        searchAllDVDs();
                        break;
                    case 6:
                        keepGoing = false;
                        break;

                    default:
                        unknownCommand();

                }

            }

            exitMessage();
        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createDVD() throws DVDLibraryDaoException {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getDVDId(), newDVD);
        view.displayDVDSuccessBanner();
    }

    private void listDVDs() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);

    }

    private void editDVD() throws DVDLibraryDaoException {
        view.displaiDisplayDVDBanner();
        String dvdId = view.getDVDIdChoice();
        DVD dvd = dao.getDVD(dvdId);
        view.displayDVD(dvd);

    }

    private void searchAllDVDs() throws DVDLibraryDaoException {
        view.displaySearchDVDBanner();
        String searchTitle = view.getSearchDVDTitle();
        dao.searchDVD(searchTitle);

    }

    private void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String dvdId = view.getDVDIdChoice();
        dao.removrDVD(dvdId);
        view.displayRemoveSuccessBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();

    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
