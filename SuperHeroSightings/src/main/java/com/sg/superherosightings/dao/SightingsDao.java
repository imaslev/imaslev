/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Sightings;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ivaylomaslev
 */
public interface SightingsDao {
    
    Sightings getSightingsById(int id);

    List<Sightings> getAllSightings();

    Sightings addSighting(Sightings sighting);

    void updateSighting(Sightings sighting);

    void deleteSightingById(int id);

    List<Sightings> getSightingsByDate(LocalDate date);

    List<Sightings> getLastTenSightings();
    
}
