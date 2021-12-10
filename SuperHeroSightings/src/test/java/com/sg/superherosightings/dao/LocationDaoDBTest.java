/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Sightings;
import com.sg.superherosightings.dto.Superpower;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ivaylomaslev
 */
@SpringBootTest
public class LocationDaoDBTest {

    @Autowired
    SuperpowerDao powerDao;

    @Autowired
    HeroDao heroDao;

    @Autowired
    OrganizationDao orgDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SightingsDao sightingDao;

    public LocationDaoDBTest() {
    }

    @BeforeEach
    public void setUp() {
        List<Sightings> sightings = sightingDao.getAllSightings();
        for (Sightings sighting : sightings) {
            sightingDao.deleteSightingById(sighting.getId());
        }

        List<Hero> heros = heroDao.getAllHeros();
        for (Hero hero : heros) {
            heroDao.deleteHeroById(hero.getId());
        }

        List<Superpower> powers = powerDao.getAllPowers();
        for (Superpower power : powers) {
            powerDao.deletePowerById(power.getId());
        }

        List<Location> locations = locationDao.getAllLocations();
        for (Location location : locations) {
            locationDao.deleteLocationById(location.getId());
        }
    }

    @Test
    public void testAddGetLocation() {
        Location location = new Location();
        location.setName("Somewhere");
        location.setDescription("d");
        location.setAddress("a");
        location.setLatitude("10");
        location.setLongitude("11");
        location = locationDao.addLocation(location);

        Location fromDao = locationDao.getLocationById(location.getId());
        assertEquals(location.getId(), fromDao.getId(), "locationId must match DB"); /* Matching by id because assert equals */
       // assertEquals(location, fromDao, "location must to DB");
    }

    @Test
    public void testGetAllLocations() {
        Location location = new Location();
        location.setName("Somewhere");
        location.setDescription("d");
        location.setAddress("a");
        location.setLatitude("10.3");
        location.setLongitude("11.1");
        location = locationDao.addLocation(location);

        Location location2 = new Location();
        location2.setName("Another Place");
        location2.setDescription("d");
        location2.setAddress("a");
        location2.setLatitude("10.4");
        location2.setLongitude("10.8");
        location2 = locationDao.addLocation(location2);

        List<Location> locations = locationDao.getAllLocations();
        assertEquals(2, locations.size());
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(location2));
    }

    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setName("Somewhere");
        location.setDescription("d");
        location.setAddress("a");
        location.setLatitude("10.3");
        location.setLongitude("11.1");
        location = locationDao.addLocation(location);

        Location fromDao = locationDao.getLocationById(location.getId());
        assertEquals(location, fromDao);

        location.setName("Somewhere Else");
        locationDao.updateLocation(location);
        assertNotEquals(location, fromDao);

        fromDao = locationDao.getLocationById(location.getId());
        assertEquals(location, fromDao);
    }

    @Test
    public void testDeleteLocation() {
        Superpower power = new Superpower();
        power.setName("Hero strength");
        power = powerDao.addPower(power);

        Hero hero = new Hero();
        hero.setName("Superman");
        hero.setDescription("Kal-El");
        hero.setPower(power);
        hero.setOrganizations(new ArrayList<Organization>());
        hero = heroDao.addHero(hero);

        Location location = new Location();
        location.setName("Somewhere");
        location.setDescription("d");
        location.setAddress("a");
        location.setLatitude("10.3");
        location.setLongitude("11.1");
        location = locationDao.addLocation(location);

        Sightings sighting = new Sightings();
        sighting.setSuperhero(hero);
        sighting.setLocation(location);
        sighting.setDate(LocalDate.now());
        sighting = sightingDao.addSighting(sighting);

        locationDao.deleteLocationById(location.getId());

        Sightings sightingFromDao = sightingDao.getSightingsById(sighting.getId());
        assertNull(sightingFromDao);

        Location locationFromDao = locationDao.getLocationById(location.getId());
        assertNull(locationFromDao);
    }

    @Test
    public void testGetLocationByHero() {
        Superpower power = new Superpower();
        power.setName("Hero strength");
        power = powerDao.addPower(power);

        Hero hero = new Hero();
        hero.setName("Superman");
        hero.setDescription("Kal-El");
        hero.setPower(power);
        hero.setOrganizations(new ArrayList<Organization>());
        hero = heroDao.addHero(hero);

        Location location = new Location();
        location.setName("Somewhere");
        location.setDescription("d");
        location.setAddress("a");
        location.setLatitude("10.3");
        location.setLongitude("11.1");
        location = locationDao.addLocation(location);

        Sightings sighting = new Sightings();
        sighting.setSuperhero(hero);
        sighting.setLocation(location);
        sighting.setDate(LocalDate.now());
        sighting = sightingDao.addSighting(sighting);

        List<Location> locations = locationDao.getLocationsByHero(hero);
        assertTrue(locations.contains(location));
    }

}
