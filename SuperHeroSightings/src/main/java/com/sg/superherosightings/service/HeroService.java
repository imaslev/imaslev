/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Sightings;
import com.sg.superherosightings.dto.Superpower;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ivaylomaslev
 */
public interface HeroService {
    
    /* Superpower */
    Superpower getSuperpowerById(int id);

    List<Superpower> getAllSuperpowers();

    Superpower addSuperpower(Superpower power);

    void updateSuperpower(Superpower power);

    void deleteSuperpowerById(int id);

    Superpower getSuperpowerByHero(Hero superhero);

    /* Hero */
    Hero getHeroById(int id);

    List<Hero> getAllHeros();

    Hero addHero(Hero superhero);

    void updateHero(Hero superhero);

    void deleteHeroById(int id);

    List<Hero> getHerosByLocation(Location location);

    List<Hero> getHerosByOrganization(Organization organization);

    /* Organization */
    Organization getOrgById(int id);

    List<Organization> getAllOrgs();

    Organization addOrg(Organization organization);

    void updateOrg(Organization organization);

    void deleteOrgById(int id);

    List<Organization> getOrgByHero(Hero superhero);

    /* Location */
    Location getLocationById(int id);

    List<Location> getAllLocations();

    Location addLocation(Location location);

    void updateLocation(Location location);

    void deleteLocationById(int id);

    List<Location> getLocationsByHero(Hero superhero);

    /* Sighting */
    Sightings getSightingsById(int id);

    List<Sightings> getAllSightings();

    Sightings addSightings(Sightings sighting);

    void updateSightings(Sightings sighting);

    void deleteSightingsById(int id);

    List<Sightings> getSightingsByDate(LocalDate date);

    List<Sightings> getLastTenSightings();

    
}
