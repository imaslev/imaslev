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
import java.time.Month;
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
public class SightingsDaoDBTest {

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

    public SightingsDaoDBTest() {
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
    public void testAddGetSightings() {
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
        sighting.setDate(LocalDate.of(2021, Month.JULY, 4));
        sighting = sightingDao.addSighting(sighting);

        Sightings fromDao = sightingDao.getSightingsById(sighting.getId());
        assertEquals(sighting, fromDao);
    }

    @Test
    public void testGetAllSightings() {
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
        location.setDescription("");
        location.setAddress("");
        location.setLatitude("10.3");
        location.setLongitude("11.1");
        location = locationDao.addLocation(location);

        Sightings sighting = new Sightings();
        sighting.setSuperhero(hero);
        sighting.setLocation(location);
        sighting.setDate(LocalDate.of(2021, Month.JULY, 4));
        sighting = sightingDao.addSighting(sighting);

        Sightings sighting2 = new Sightings();
        sighting2.setSuperhero(hero);
        sighting2.setLocation(location);
        sighting2.setDate(LocalDate.of(2021, Month.JULY, 5));
        sighting2 = sightingDao.addSighting(sighting2);

        List<Sightings> sightings = sightingDao.getAllSightings();
        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));
    }

    @Test
    public void testUpdateSightings() {
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
        sighting.setDate(LocalDate.of(2021, Month.JULY, 4));
        sighting = sightingDao.addSighting(sighting);

        Sightings fromDao = sightingDao.getSightingsById(sighting.getId());
        assertEquals(sighting, fromDao);

        sighting.setDate(LocalDate.of(2021, Month.JULY, 6));
        sightingDao.updateSighting(sighting);
        assertNotEquals(sighting, fromDao);

        fromDao = sightingDao.getSightingsById(sighting.getId());
        assertEquals(sighting, fromDao);
    }

    @Test
    public void testDeleteSightings() {
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
        sighting.setDate(LocalDate.of(2021, Month.JULY, 4));
        sighting = sightingDao.addSighting(sighting);

        sightingDao.deleteSightingById(sighting.getId());

        Sightings fromDao = sightingDao.getSightingsById(sighting.getId());
        assertNull(fromDao);
    }

    @Test
    public void testGetSightingsByDate() {
        Superpower power = new Superpower();
        power.setName("Hero strength");
        power = powerDao.addPower(power);

        Hero hero = new Hero();
        hero.setName("Superman");
        hero.setDescription("Kal-El");
        hero.setPower(power);
        hero.setOrganizations(new ArrayList<Organization>());
        hero = heroDao.addHero(hero);

        Hero hero2 = new Hero();
        hero2.setName("Batman");
        hero2.setDescription("The Dark Knight");
        hero2.setOrganizations(new ArrayList<Organization>());
        hero2 = heroDao.addHero(hero2);

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
        sighting.setDate(LocalDate.of(2021, Month.JULY, 4));
        sighting = sightingDao.addSighting(sighting);

        Sightings sighting2 = new Sightings();
        sighting2.setSuperhero(hero2);
        sighting2.setLocation(location);
        sighting2.setDate(LocalDate.of(2021, Month.JULY, 4));
        sighting2 = sightingDao.addSighting(sighting2);
        
        Sightings sighting3 = new Sightings();
        sighting3.setSuperhero(hero2);
        sighting3.setLocation(location);
        sighting3.setDate(LocalDate.of(2021, Month.JULY, 5));
        sighting3 = sightingDao.addSighting(sighting3);

        List<Sightings> sightings = sightingDao.getSightingsByDate(LocalDate.of(2021, Month.JULY, 4));

        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));
    }

}
