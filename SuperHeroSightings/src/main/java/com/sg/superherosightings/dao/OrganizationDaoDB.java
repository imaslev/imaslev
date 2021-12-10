/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.HeroDaoDB.HeroMapper;
import com.sg.superherosightings.dao.SuperpowerDaoDB.PowerMapper;
import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ivaylomaslev
 */
@Component
public class OrganizationDaoDB implements OrganizationDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Organization getOrgById(int id) {
        try {
            final String GET_ORG_BY_ID = "SELECT * FROM Organization WHERE id = ?";
            Organization organization = jdbc.queryForObject(GET_ORG_BY_ID, new OrganizationMapper(), id);
            organization.setHeros(getHerosForOrganization(organization));
            return organization;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrgs() {
        final String GET_ALL_ORGS = "SELECT * FROM Organization";
        List<Organization> organizations = jdbc.query(GET_ALL_ORGS, new OrganizationMapper());
        for (Organization org : organizations) {
            org.setHeros(getHerosForOrganization(org));
        }
        return organizations;
    }

    @Override
    @Transactional
    public Organization addOrg(Organization organization) {
        final String ADD_ORG = "INSERT INTO Organization(name, description, address, contact) VALUES(?,?,?,?)";
        jdbc.update(ADD_ORG,
                organization.getName(),
                organization.getDescription(),
                organization.getAddress(),
                organization.getContact());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setId(newId);
        insertOrganizationMembers(organization);
        return organization;
    }

    @Override
    @Transactional
    public void updateOrg(Organization organization) {
        final String UPDATE_ORG = "UPDATE Organization SET name = ?, description = ?, "
                + "address = ?, contact = ? WHERE id = ?";
        jdbc.update(UPDATE_ORG,
                organization.getName(),
                organization.getDescription(),
                organization.getAddress(),
                organization.getContact(),
                organization.getId());

        final String DELETE_ORG_MEMBERS = "DELETE FROM OrganizationMember WHERE organizationId = ?";
        jdbc.update(DELETE_ORG_MEMBERS, organization.getId());
        insertOrganizationMembers(organization);
    }

    @Override
    @Transactional
    public void deleteOrgById(int id) {
        final String DELETE_ORG_MEMBERS = "DELETE FROM OrganizationMember WHERE organizationId = ?";
        jdbc.update(DELETE_ORG_MEMBERS, id);

        final String DELETE_ORG = "DELETE FROM Organization WHERE id = ?";
        jdbc.update(DELETE_ORG, id);
    }

    @Override
    public List<Organization> getOrgByHero(Hero superhero) {
        final String GET_ORG_BY_HERO = "SELECT o.id, o.name, o.description, o.address, o.contact "
                + "FROM OrganizationMember om "
                + "JOIN Organization o ON om.organizationId = o.id "
                + "WHERE om.heroId = ?";
        List<Organization> orgs = jdbc.query(GET_ORG_BY_HERO, new OrganizationMapper(), superhero.getId());
        for (Organization org : orgs) {
            //org.setHeros(getHerosForOrganization(org));
            org.setHeros(Arrays.asList(superhero));
        }
        return orgs;

    }

    private List<Hero> getHerosForOrganization(Organization organization) {
        final String GET_HEROS = "SELECT h.id, h.name, h.description, h.superpowerId "
                + "FROM OrganizationMember om "
                + "JOIN Hero h ON om.heroId = h.id "
                + "WHERE om.organizationId = ?";
        List<Hero> heros = jdbc.query(GET_HEROS, new HeroMapper(), organization.getId());
        for (Hero superhero : heros) {
            superhero.setPower(getPowerForHero(superhero.getId()));
        }
        return heros;
    }

    private void insertOrganizationMembers(Organization organization) {
        final String INSERT_ORG_MEMBERS = "INSERT INTO OrganizationMember(heroId, organizationId ) VALUES(?,?)";
        for (Hero superhero : organization.getHeros()) {
            jdbc.update(INSERT_ORG_MEMBERS, superhero.getId(), organization.getId());
        }
    }

    private Superpower getPowerForHero(int id) {
        try {
            final String sql = "SELECT p.id, p.name FROM Superpower p "
                    + "JOIN Hero h ON p.id = h.superpowerId WHERE h.id = ?";
            return jdbc.queryForObject(sql, new PowerMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization organization = new Organization();
            organization.setId(rs.getInt("id"));
            organization.setName(rs.getString("name"));
            organization.setDescription(rs.getString("description"));
            organization.setAddress(rs.getString("address"));
            organization.setContact(rs.getString("contact"));
            return organization;
        }
    }

}
