/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import java.util.List;

/**
 *
 * @author ivaylomaslev
 */
public interface HeroDao {

    Hero getHeroById(int id);

    List<Hero> getAllHeros();

    Hero addHero(Hero superhero);

    void updateHero(Hero superhero);

    void deleteHeroById(int id);

    List<Hero> getHerosByLocation(Location location);

    List<Hero> getHerosByOrganization(Organization organization);
    
}
