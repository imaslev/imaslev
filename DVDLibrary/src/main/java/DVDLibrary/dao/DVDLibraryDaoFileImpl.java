/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDLibrary.dao;

import DVDLibrary.Dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ivaylomaslev
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    private Map<String, DVD> dvdsHashMap = new HashMap<>();

    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    private DVD searchDVD;

    /**
     *
     * @param dvdId
     * @param dvd
     * @return
     * @throws DVDLibraryDaoException
     */
    @Override
    public DVD addDVD(String dvdId, DVD dvd)
            throws DVDLibraryDaoException {

        DVD newDVD = dvdsHashMap.put(dvdId, dvd);

        writeLibrary();
        return newDVD;
    }
    @Override
    public DVD editDVD(String title, DVD edited)throws DVDLibraryDaoException{
        DVD editedDVD = dvdsHashMap.put(title, edited);
        
        writeLibrary();
        return editedDVD;
    }
    
  
    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException {

        loadLibrary();
        return new ArrayList<DVD>(dvdsHashMap.values());
    }

    @Override
    public DVD getDVD(String dvdId) throws DVDLibraryDaoException {

        loadLibrary();
        return dvdsHashMap.get(dvdId);
    }

    @Override
    public DVD removrDVD(String dvdId) throws DVDLibraryDaoException {

        DVD removedDVD = dvdsHashMap.remove(dvdId);

        writeLibrary();
        return removedDVD;
    }

    private void loadLibrary() throws DVDLibraryDaoException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {

            throw new DVDLibraryDaoException("-_- Cold not load library data in to memory.", e);

        }

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            DVD currentDVD = new DVD(currentTokens[0]);

            currentDVD.setDVDTitle(currentTokens[1]);
            currentDVD.setDVDDirector(currentTokens[2]);
            currentDVD.setGenres(currentTokens[3]);

            dvdsHashMap.put(currentDVD.getDVDId(), currentDVD);

        }

        scanner.close();
    }

    private void writeLibrary() throws DVDLibraryDaoException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {

            throw new DVDLibraryDaoException("Colud not save DVD data.", e);

        }

        List<DVD> dvdList = this.getAllDVDs();

        for (DVD currentDVD : dvdList) {
            out.print(currentDVD.getDVDId() + DELIMITER + currentDVD.getDVDTitle() + DELIMITER + currentDVD.getDVDDirector() + DELIMITER + currentDVD.getGenres());

            out.flush();

        }

        out.close();
    }

    public void searchDVD( String searchString){
         
         for(DVD value : dvdsHashMap.values()){
             if(value.getDVDTitle().toLowerCase().contains(searchString.toLowerCase())){
                 System.out.println(value.getDVDTitle());
                 
             }
             
             /*
             https://stackoverflow.com/questions/16604765/ignore-case-for-contains-for-a-string-in-java/16604776
             */
         }
         
         
         
     }

     
    }
    

    
   
        
    


