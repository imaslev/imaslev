/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDLibrary.Dto;

/**
 *
 * @author ivaylomaslev
 */
public class DVD {
    
     private String dvdTitle;
    private String dvdDirector;
    private String dvdId;
    
    private String genres;
    

    public DVD(String dvdId) {
        this.dvdId = dvdId;

    }

    public String getDVDTitle() {
        return dvdTitle;

    }

    public void setDVDTitle(String dvdTitle) {
        this.dvdTitle = dvdTitle;

    }

    public String getDVDDirector() {
        return dvdDirector;
    }

    public void setDVDDirector(String dvdDirector) {
        this.dvdDirector = dvdDirector;
    }

    public String getDVDId() {
        return dvdId;
    }
    
    public void setDVDId(){
        this.dvdId = dvdId;
    }
    
  
    public String getGenres() {
        return genres;

    }
    

    public void setGenres(String genres) {
        this.genres = genres;

    }

   

    
}
