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
public class HeroDaoDBTest {
    
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
    
    public HeroDaoDBTest() {
    
    }
    
 @BeforeEach
    public void setUp() {
        List<Sightings> sightings = sightingDao.getAllSightings();
        for (Sightings sighting : sightings) {
            sightingDao.deleteSightingById(sighting.getId());
        }

        List<Location> locations = locationDao.getAllLocations();
        for (Location location : locations) {
            locationDao.deleteLocationById(location.getId());
        }

        List<Hero> heros = heroDao.getAllHeros();
        for (Hero hero : heros) {
            heroDao.deleteHeroById(hero.getId());
        }

        List<Superpower> powers = powerDao.getAllPowers();
        for (Superpower power : powers) {
            powerDao.deletePowerById(power.getId());
        }
    }
        
    @Test
    public void testAddGetHero() {
        Superpower power = new Superpower();
        power.setName("Hero strength");
        power = powerDao.addPower(power);

        Hero hero = new Hero();
        hero.setName("Superman");
        hero.setDescription("Kal-El");
        hero.setPower(power);
        hero.setOrganizations(new ArrayList<Organization>());
        hero = heroDao.addHero(hero);

        Hero fromDao = heroDao.getHeroById(hero.getId());
        //assertEquals(hero, fromDao);
        assertEquals(hero.getId(), fromDao.getId());
    
    }
    
    @Test
    public void testGetAllHeros() {
        Superpower power = new Superpower();
        power.setName("Hero strength");
        power = powerDao.addPower(power);

        Superpower power2 = new Superpower();
        power2.setName("Hero speed");
        power2 = powerDao.addPower(power2);

        Hero hero = new Hero();
        hero.setName("Superman");
        hero.setDescription("Kal-El");
        hero.setPower(power);
        hero.setOrganizations(new ArrayList<Organization>());
        hero = heroDao.addHero(hero);

        Hero hero2 = new Hero();
        hero2.setName("Captain America");
        hero2.setDescription("America's sweetheart");
        hero2.setPower(power);
        hero2.setOrganizations(new ArrayList<Organization>());
        hero2 = heroDao.addHero(hero2);

        Hero hero3 = new Hero();
        hero3.setName("Flash");
        hero3.setDescription("Fast guy in DC");
        hero3.setPower(power2);
        hero3.setOrganizations(new ArrayList<Organization>());
        hero3 = heroDao.addHero(hero3);

        List<Hero> heros = heroDao.getAllHeros();
        assertEquals(3, heros.size());
        assertTrue(heros.contains(hero));
        assertTrue(heros.contains(hero2));
        assertTrue(heros.contains(hero3));
    }
    
    @Test
    public void testUpdateHero() {
        Superpower power = new Superpower();
        power.setName("Hero strength");
        power = powerDao.addPower(power);

        Hero hero = new Hero();
        hero.setName("Superman");
        hero.setDescription("Kal-El");
        hero.setPower(power);
        hero.setOrganizations(new ArrayList<Organization>());
        hero = heroDao.addHero(hero);

        Hero fromDao = heroDao.getHeroById(hero.getId());
        assertEquals(hero.getId(), fromDao.getId());

        hero.setName("Superboy");
        heroDao.updateHero(hero);

        assertNotEquals(hero, fromDao);

        fromDao = heroDao.getHeroById(hero.getId());
        assertEquals(hero.getId(), fromDao.getId());
    }
    
    @Test
    public void testDeleteHero() {
        Superpower power = new Superpower();
        power.setName("Hero strength");
        power = powerDao.addPower(power);

        Hero hero = new Hero();
        hero.setName("Superman");
        hero.setDescription("Kal-El");
        hero.setPower(power);
        hero.setOrganizations(new ArrayList<Organization>());
        hero = heroDao.addHero(hero);

        Hero fromDao = heroDao.getHeroById(hero.getId());
        assertEquals(hero.getId(), fromDao.getId());

        heroDao.deleteHeroById(hero.getId());
        fromDao = heroDao.getHeroById(hero.getId());
        assertNull(fromDao);

    }
    
    @Test
    public void testGetHerosByLocation() {
        Superpower power = new Superpower();
        power.setName("Hero strength");
        power = powerDao.addPower(power);

        Superpower power2 = new Superpower();
        power2.setName("Hero speed");
        power2 = powerDao.addPower(power2);

        Hero hero = new Hero();
        hero.setName("Superman");
        hero.setDescription("Kal-El");
        hero.setPower(power);
        hero.setOrganizations(new ArrayList<Organization>());
        hero = heroDao.addHero(hero);

        Hero hero2 = new Hero();
        hero2.setName("Captain America");
        hero2.setDescription("America's sweetheart");
        hero2.setPower(power);
        hero2.setOrganizations(new ArrayList<Organization>());
        hero2 = heroDao.addHero(hero2);

        Hero hero3 = new Hero();
        hero3.setName("Flash");
        hero3.setDescription("Fast guy in DC");
        hero3.setPower(power2);
        hero3.setOrganizations(new ArrayList<Organization>());
        hero3 = heroDao.addHero(hero3);

        Location location = new Location();
        location.setAddress("Daily Planet address");
        location = locationDao.addLocation(location);

        Location location2 = new Location();
        location2.setAddress("Flash address");
        location2 = locationDao.addLocation(location2);

        Sightings sighting = new Sightings();
        sighting.setSuperhero(hero);
        sighting.setLocation(location);
        sighting.setDate(LocalDate.now());
        sighting = sightingDao.addSighting(sighting);

        Sightings sighting2 = new Sightings();
        sighting2.setSuperhero(hero2);
        sighting2.setLocation(location);
        sighting2.setDate(LocalDate.now());
        sighting2 = sightingDao.addSighting(sighting2);

        Sightings sighting3 = new Sightings();
        sighting3.setSuperhero(hero3);
        sighting3.setLocation(location2);
        sighting3.setDate(LocalDate.now());
        sighting3 = sightingDao.addSighting(sighting3);

        List<Hero> heros = heroDao.getHerosByLocation(location);
        assertEquals(2, heros.size());
        assertTrue(heros.contains(hero));
        assertTrue(heros.contains(hero2));
        assertFalse(heros.contains(hero3));

    }
    
    
}
