/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDLibrary.dao;

import DVDLibrary.Dto.DVD;
import java.util.List;

/**
 *
 * @author ivaylomaslev
 */
public interface DVDLibraryDao {

    DVD addDVD(String dvdId, DVD dvd)
            throws DVDLibraryDaoException;

     DVD editDVD(String title, DVD edited)
            throws DVDLibraryDaoException;

    List<DVD> getAllDVDs()
            throws DVDLibraryDaoException;

    DVD getDVD(String dvdId)
            throws DVDLibraryDaoException;

    DVD removrDVD(String dvdId)
            throws DVDLibraryDaoException;
    
    void searchDVD(String dvdTitle)
            throws DVDLibraryDaoException;
}
