/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.OrganizationDaoDB.OrganizationMapper;
import com.sg.superherosightings.dao.SuperpowerDaoDB.PowerMapper;
import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ivaylomaslev
 */
@Repository

public class HeroDaoDB implements HeroDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Hero getHeroById(int id) {
        try {
            final String GET_HERO_BY_ID = "SELECT * FROM hero WHERE id = ?";
            Hero superhero = jdbc.queryForObject(GET_HERO_BY_ID, new HeroMapper(), id);
            superhero.setPower(getPowerForHero(id));
            superhero.setOrganizations(getOrganizationsForHero(id));
            return superhero;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Hero> getAllHeros() {
        final String GET_ALL_HEROS = "SELECT * FROM Hero";
        List<Hero> heros = jdbc.query(GET_ALL_HEROS, new HeroMapper());
        for (Hero superhero : heros) {
            superhero.setPower(getPowerForHero(superhero.getId()));
            superhero.setOrganizations(getOrganizationsForHero(superhero.getId()));
        }
        return heros;
    }

    @Override
    @Transactional
    public Hero addHero(Hero superhero) {
        final String ADD_HERO = "INSERT INTO Hero(name, description, superpowerId) VALUES(?,?,?)";
        if (superhero.getPower() != null) {
            jdbc.update(ADD_HERO,
                    superhero.getName(),
                    superhero.getDescription(),
                    superhero.getPower().getId());
        } else {
            jdbc.update(ADD_HERO,
                    superhero.getName(),
                    superhero.getDescription(),
                    null);
        }
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superhero.setId(newId);
        insertOrganizationMember(superhero);
        return superhero;
    }

    @Override
    public void updateHero(Hero superhero) {
        final String UPDATE_HERO = "UPDATE Hero SET name = ?, description= ?, superpowerId = ? WHERE id = ?";
        if (superhero.getPower() != null) {
            jdbc.update(UPDATE_HERO,
                    superhero.getName(),
                    superhero.getDescription(),
                    superhero.getPower().getId(),
                    superhero.getId());
        } else {
            jdbc.update(UPDATE_HERO,
                    superhero.getName(),
                    superhero.getDescription(),
                    null,
                    superhero.getId());
        }
        final String DELETE_ORGANIZATION_MEMBER = "DELETE FROM OrganizationMember WHERE heroId =?";
        jdbc.update(DELETE_ORGANIZATION_MEMBER, superhero.getId());
        insertOrganizationMember(superhero);
    }

    @Override
    public void deleteHeroById(int id) {
        final String DELETE_SIGHTINGS = "DELETE FROM Sightings WHERE heroId = ?";
        jdbc.update(DELETE_SIGHTINGS, id);

        final String DELETE_ORGANIZATION_MAMBER = "DELETE FROM OrganizationMember WHERE heroId = ?";
        jdbc.update(DELETE_ORGANIZATION_MAMBER, id);

        final String DELETE_HERO = "DELETE FROM Hero WHERE id = ?";
        jdbc.update(DELETE_HERO, id);

    }

    /**
     *
     * @param location
     * @return
     */
    @Transactional
    @Override
    public List<Hero> getHerosByLocation(Location location) {
        final String GET_HEROS_BY_LOCATION = "SELECT DISTINCT h.id, h. name, h.description, h.superpowerId"
                + " FROM Sightings st "
                + " JOIN  Hero h ON st.heroId = h.id "
                + "WHERE st.locationId = ?";
        List<Hero> heros = jdbc.query(GET_HEROS_BY_LOCATION, new HeroMapper(), location.getId());
        for (Hero superhero : heros) {
            superhero.setPower(getPowerForHero(superhero.getId()));
            superhero.setOrganizations(getOrganizationsForHero(superhero.getId()));
        }
        return heros;

    }

    @Override
    public List<Hero> getHerosByOrganization(Organization organization) {
        final String GET_HEROS_BY_ORG = "SELECT h.id, h.name, h.description, h.superpowerId"
                + "FROM OrganizationMember om"
                + "JOIN Hero h on om.heroId = h.id"
                + "WHERE om.organizationId = ?";
        List<Hero> heros = jdbc.query(GET_HEROS_BY_ORG, new HeroMapper(), organization.getId());
        for (Hero superhero : heros) {
            superhero.setPower(getPowerForHero(superhero.getId()));
            superhero.setOrganizations(getOrganizationsForHero(superhero.getId()));

        }
        return heros;
    }

    private List<Organization> getOrganizationsForHero(int id) {
        final String GET_ORGANIZATION_FOR_HERO = "SELECT o.id, o.name, o.description, o.address, o.contact "
                + "FROM OrganizationMember om "
                + "JOIN Organization o ON om.organizationId = o.id "
                + "WHERE om.heroId = ?";
        List<Organization> organizations = jdbc.query(GET_ORGANIZATION_FOR_HERO, new OrganizationMapper(), id);
        for (Organization organization : organizations) {
            organization.setHeros(getHerosForOrganization(organization));
        }
        return organizations;
    }

    private Superpower getPowerForHero(int id) {
        try {
            final String GET_SUPERPOWER_FOR_HERO = "SELECT p.id, p.name FROM Superpower p "
                    + "JOIN Hero h ON p.id = h.superpowerId WHERE h.id = ?";
            return jdbc.queryForObject(GET_SUPERPOWER_FOR_HERO, new PowerMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private void insertOrganizationMember(Hero superhero) {
        final String INSERT_ORGANIZATION_MEMBER = "INSERT INTO OrganizationMember(heroId, organizationId) VALUES(?,?)";
        for (Organization organization : superhero.getOrganizations()) {
            jdbc.update(INSERT_ORGANIZATION_MEMBER, superhero.getId(), organization.getId());
        }
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

    public final static class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int i) throws SQLException {
            Hero superhero = new Hero();
            superhero.setId(rs.getInt("id"));
            superhero.setName(rs.getString("name"));
            superhero.setDescription(rs.getString("description"));
            return superhero;
        }

    }

}
