/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Superpower;
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
public class SuperpowerDaoDBTest {

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

    public SuperpowerDaoDBTest() {
    }

    @BeforeEach
    public void setUp() {
        List<Hero> heros = heroDao.getAllHeros();
        for (Hero superhero : heros) {
            heroDao.deleteHeroById(superhero.getId());
        }

        List<Superpower> powers = powerDao.getAllPowers();
        for (Superpower power : powers) {
            powerDao.deletePowerById(power.getId());
        }
    }

    @Test
    public void testAddGetSuperpower() {
        Superpower power = new Superpower();
        power.setName("Super strength");
        power = powerDao.addPower(power);

        Superpower fromDao = powerDao.getPowerById(power.getId());
        assertEquals(power, fromDao);
    }

    @Test
    public void testGetAllSuperpowers() {
        Superpower power = new Superpower();
        power.setName("Super strength");
        power = powerDao.addPower(power);

        Superpower power2 = new Superpower();
        power2.setName("Flying");
        power2 = powerDao.addPower(power2);

        Superpower power3 = new Superpower();
        power3.setName("Hero speed");
        power3 = powerDao.addPower(power3);

        List<Superpower> powers = powerDao.getAllPowers();

        assertEquals(3, powers.size());
        assertTrue(powers.contains(power));
        assertTrue(powers.contains(power2));
        assertTrue(powers.contains(power3));
    }

    @Test
    public void testUpdateSuperpower() {
        Superpower power = new Superpower();
        power.setName("Super strength");
        power = powerDao.addPower(power);

        Superpower fromDao = powerDao.getPowerById(power.getId());
        assertEquals(power, fromDao);

        power.setName("Hero speed");
        powerDao.updatePower(power);

        assertNotEquals(power, fromDao);

        fromDao = powerDao.getPowerById(power.getId());
        assertEquals(power, fromDao);
    }

    @Test
    public void testDeleteSuperpower() {
        Superpower power = new Superpower();
        power.setName("Hero strength");
        power = powerDao.addPower(power);

        Hero superhero = new Hero();
        superhero.setName("Superman");
        superhero.setDescription("The face of superheros (not really!)");
        superhero.setPower(power);
        superhero.setOrganizations(new ArrayList<Organization>());
        superhero = heroDao.addHero(superhero);

        Superpower fromDao = powerDao.getPowerById(power.getId());
        assertEquals(power, fromDao);

        powerDao.deletePowerById(power.getId());

        fromDao = powerDao.getPowerById(power.getId());
        assertNull(fromDao);

        superhero = heroDao.getHeroById(superhero.getId());
        assertNull(superhero.getPower());

    }

}
