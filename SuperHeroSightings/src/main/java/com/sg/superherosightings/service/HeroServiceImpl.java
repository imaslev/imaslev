/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.HeroDao;
import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.SightingsDao;
import com.sg.superherosightings.dao.SuperpowerDao;
import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Sightings;
import com.sg.superherosightings.dto.Superpower;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ivaylomaslev
 */
@Service
public class HeroServiceImpl implements HeroService{
    
    
    @Autowired
    SuperpowerDao superpowerDao;

    @Autowired
    HeroDao heroDao;

    @Autowired
    OrganizationDao orgDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SightingsDao sightingsDao;


    @Override
    public Superpower getSuperpowerById(int id) {
        return superpowerDao.getPowerById(id);
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        return superpowerDao.getAllPowers();
    }

    @Override
    public Superpower addSuperpower(Superpower power) {
        return superpowerDao.addPower(power);
    }

    @Override
    public void updateSuperpower(Superpower power) {
        superpowerDao.updatePower(power);
    }

    @Override
    public void deleteSuperpowerById(int id) {
        superpowerDao.deletePowerById(id);
    }

    @Override
    public Superpower getSuperpowerByHero(Hero superhero) {
        return superpowerDao.getPowerByHero(superhero);
    }

    @Override
    public Hero getHeroById(int id) {
        return heroDao.getHeroById(id);
    }

    @Override
    public List<Hero> getAllHeros() {
        return heroDao.getAllHeros();
    }

    @Override
    public Hero addHero(Hero superhero) {
        return heroDao.addHero(superhero);
    }

    @Override
    public void updateHero(Hero superhero) {
        heroDao.updateHero(superhero);
    }

    @Override
    public void deleteHeroById(int id) {
        heroDao.deleteHeroById(id);
    }

    @Override
    public List<Hero> getHerosByLocation(Location location) {
        return heroDao.getHerosByLocation(location);
    }

    @Override
    public List<Hero> getHerosByOrganization(Organization organization) {
        return heroDao.getHerosByOrganization(organization);
    }

    @Override
    public Organization getOrgById(int id) {
        return orgDao.getOrgById(id);
    }

    @Override
    public List<Organization> getAllOrgs() {
        return orgDao.getAllOrgs();
    }

    @Override
    public Organization addOrg(Organization organization) {
        return orgDao.addOrg(organization);
    }

    @Override
    public void updateOrg(Organization organization) {
        orgDao.updateOrg(organization);
    }

    @Override
    public void deleteOrgById(int id) {
        orgDao.deleteOrgById(id);
    }

    @Override
    public List<Organization> getOrgByHero(Hero superhero) {
        return orgDao.getOrgByHero(superhero);
    }

    @Override
    public Location getLocationById(int id) {
        return locationDao.getLocationById(id);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    @Override
    public Location addLocation(Location location) {
        return locationDao.addLocation(location);
    }

    @Override
    public void updateLocation(Location location) {
        locationDao.addLocation(location);
    }

    @Override
    public void deleteLocationById(int id) {
        locationDao.deleteLocationById(id);
    }

    @Override
    public List<Location> getLocationsByHero(Hero superhero) {
        return locationDao.getLocationsByHero(superhero);
    }

    @Override
    public Sightings getSightingsById(int id) {
        return sightingsDao.getSightingsById(id);
    }

    @Override
    public List<Sightings> getAllSightings() {
        return sightingsDao.getAllSightings();
    }

    @Override
    public Sightings addSightings(Sightings sighting) {
        return sightingsDao.addSighting(sighting);
    }

    @Override
    public void updateSightings(Sightings sighting) {
        sightingsDao.updateSighting(sighting);
    }

    @Override
    public void deleteSightingsById(int id) {
        sightingsDao.deleteSightingById(id);
    }

    @Override
    public List<Sightings> getSightingsByDate(LocalDate date) {
        return sightingsDao.getSightingsByDate(date);
    }

    @Override
    public List<Sightings> getLastTenSightings() {
        return sightingsDao.getLastTenSightings();
    }
    
}
