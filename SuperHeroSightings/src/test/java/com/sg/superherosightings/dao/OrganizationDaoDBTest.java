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
public class OrganizationDaoDBTest {

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

    public OrganizationDaoDBTest() {
    }

    @BeforeEach
    public void setUp() {
        List<Organization> orgs = orgDao.getAllOrgs();
        for (Organization org : orgs) {
            orgDao.deleteOrgById(org.getId());
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
    public void testAddGetOrg() {
        Organization org = new Organization();
        org.setName("The Avengers");
        org.setDescription("Best group, no doubt.");
        org.setAddress("a");
        org.setContact("c");
        org.setHeros(new ArrayList<Hero>());
        org = orgDao.addOrg(org);

        Organization fromDao = orgDao.getOrgById(org.getId());
        assertEquals(org, fromDao);
    }

    @Test
    public void testGetAllOrgs() {
        Organization org = new Organization();
        org.setName("The Avengers");
        org.setDescription("Best group, no doubt.");
        org.setAddress("a");
        org.setContact("c");
        org.setHeros(new ArrayList<Hero>());
        org = orgDao.addOrg(org);

        Organization org2 = new Organization();
        org2.setName("Justice League");
        org2.setDescription("They're okay.");
        org2.setAddress("a");
        org2.setContact("c");
        org2.setHeros(new ArrayList<Hero>());
        org2 = orgDao.addOrg(org2);

        List<Organization> orgs = orgDao.getAllOrgs();
        assertEquals(2, orgs.size());
        assertTrue(orgs.contains(org));
        assertTrue(orgs.contains(org2));
    }

    @Test
    public void testUpdateOrg() {
        Organization org = new Organization();
        org.setName("The Avengers");
        org.setDescription("Best group, no doubt.");
        org.setAddress("a");
        org.setContact("c");
        org.setHeros(new ArrayList<Hero>());
        org = orgDao.addOrg(org);

        Organization fromDao = orgDao.getOrgById(org.getId());
        assertEquals(org, fromDao);

        org.setDescription("My favorite group ever");
        orgDao.updateOrg(org);
        assertNotEquals(org, fromDao);

        fromDao = orgDao.getOrgById(org.getId());
        assertEquals(org, fromDao);
    }

   /* @Test
    public void testDeleteOrg() {
        Organization org = new Organization();
        org.setName("The Avengers");
        org.setDescription("Best group, no doubt.");
        org.setAddress("a");
        org.setContact("c");
        org.setHeros(new ArrayList<Hero>());
        org = orgDao.addOrg(org);

        Superpower power = new Superpower();
        power.setName("Hero strength");
        power = powerDao.addPower(power);

        Hero hero = new Hero();
        hero.setName("Captain America");
        hero.setDescription("America's sweetheart");
        hero.setPower(power);
        hero.setOrganizations(new ArrayList<Organization>());
        hero = heroDao.addHero(hero);

        org.getHeros().add(hero);
        hero.getOrganizations().add(org);
        orgDao.updateOrg(org);
        heroDao.updateHero(hero);

        Organization fromDao = orgDao.getOrgById(org.getId());
        assertEquals(org, fromDao);

        orgDao.deleteOrgById(org.getId());
        fromDao = orgDao.getOrgById(org.getId());
        assertNull(fromDao);
    }*/
     @Test
    public void testDeleteOrgWithNoHero() {
        Organization org = new Organization();
        org.setName("The Avengers");
        org.setDescription("Best group, no doubt.");
        org.setAddress("a");
        org.setContact("c");
        org.setHeros(new ArrayList<Hero>());
        org = orgDao.addOrg(org);

       

        Organization fromDao = orgDao.getOrgById(org.getId());
       

        orgDao.deleteOrgById(org.getId());
        fromDao = orgDao.getOrgById(org.getId());
        assertNull(fromDao);
    }
    
    

    @Test
    public void testGetOrgByHero() {
        
        
        Superpower power = new Superpower();
        power.setName("Hero strength");
        power = powerDao.addPower(power);

        Hero hero = new Hero();
        hero.setName("Captain America");
        hero.setDescription("America's sweetheart");
        hero.setPower(power);
        hero.setOrganizations(new ArrayList<Organization>());
        hero = heroDao.addHero(hero);
        
        Organization org = new Organization();
        org.setName("The Avengers");
        org.setDescription("Best group, no doubt.");
        org.setAddress("a");
        org.setContact("c");
        org.setHeros(new ArrayList<Hero>());
        org = orgDao.addOrg(org);


        //hero.getOrganizations().add(org);
        //heroDao.updateHero(hero);

        org.getHeros().add(hero);
        orgDao.updateOrg(org);

        List<Organization> orgs = orgDao.getOrgByHero(hero);
        assertEquals(1, orgs.size());
        assertTrue(orgs.contains(org));

    }
}
