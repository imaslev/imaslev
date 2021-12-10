/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Superpower;
import java.util.List;

/**
 *
 * @author ivaylomaslev
 */
public interface SuperpowerDao {
    
    Superpower getPowerById(int id);

    List<Superpower> getAllPowers();

    Superpower addPower(Superpower power);

    void updatePower(Superpower power);

    void deletePowerById(int id);

    Superpower getPowerByHero(Hero superhero);
    
}
