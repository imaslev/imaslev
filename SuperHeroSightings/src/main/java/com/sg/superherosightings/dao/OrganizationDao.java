/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Organization;
import java.util.List;

/**
 *
 * @author ivaylomaslev
 */
public interface OrganizationDao {
    
    Organization getOrgById(int id);

    List<Organization> getAllOrgs();

    Organization addOrg(Organization organization);

    void updateOrg(Organization organization);

    void deleteOrgById(int id);

    List<Organization> getOrgByHero(Hero superhero);
    
}
