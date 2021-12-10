/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.HeroDaoDB.HeroMapper;
import com.sg.superherosightings.dao.LocationDaoDB.LocationMapper;
import com.sg.superherosightings.dao.OrganizationDaoDB.OrganizationMapper;
import com.sg.superherosightings.dao.SuperpowerDaoDB.PowerMapper;
import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Sightings;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
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
public class SightingsDaoDB implements SightingsDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Sightings getSightingsById(int id) {
        try {
            final String GET_SIGHTINGS_BY_ID = "SELECT * FROM Sightings WHERE id = ?";
            Sightings sightings = jdbc.queryForObject(GET_SIGHTINGS_BY_ID, new SightingsMapper(), id);
            sightings.setSuperhero(getHeroForSightings(sightings));
            sightings.setLocation(getLocationForSighting(sightings));
            return sightings;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sightings> getAllSightings() {
        final String GET_ALL_SIGHTINGS = "SELECT * FROM Sightings";
        List<Sightings> sightings = jdbc.query(GET_ALL_SIGHTINGS, new SightingsMapper());
        for (Sightings sighting : sightings) {
            sighting.setSuperhero(getHeroForSightings(sighting));
            sighting.setLocation(getLocationForSighting(sighting));
        }
        return sightings;
    }

    @Override
    @Transactional
    public Sightings addSighting(Sightings sightings) {
        final String ADD_SIGHTING = "INSERT INTO Sightings(heroId, locationId, date) VALUES (?,?,?)";
        jdbc.update(ADD_SIGHTING,
                sightings.getSuperhero().getId(),
                sightings.getLocation().getId(),
                Timestamp.valueOf(sightings.getDate().atTime(12, 0)));
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sightings.setId(newId);
        return sightings;
    }

    @Override
    @Transactional
    public void updateSighting(Sightings sightings) {
        final String UPDATE_SIGHTING = "UPDATE Sightings SET heroId = ?, locationId = ?, date = ? WHERE id = ?";
        jdbc.update(UPDATE_SIGHTING,
                sightings.getSuperhero().getId(),
                sightings.getLocation().getId(),
                Timestamp.valueOf(sightings.getDate().atTime(12, 0)),
                sightings.getId());
    }

    @Override
    @Transactional
    public void deleteSightingById(int id) {
        final String DELETE_SIGHTING = "DELETE FROM Sightings WHERE id = ?";
        jdbc.update(DELETE_SIGHTING, id);
    }

    @Override
    public List<Sightings> getSightingsByDate(LocalDate date) {
        final String GET_SIGHTINGS_BY_DATE = "SELECT * FROM Sightings WHERE date = ?";
        List<Sightings> sightings = jdbc.query(GET_SIGHTINGS_BY_DATE, new SightingsMapper(),
                Timestamp.valueOf(date.atTime(12, 0)));
        for (Sightings sighting : sightings) {
            sighting.setSuperhero(getHeroForSightings(sighting));
            sighting.setLocation(getLocationForSighting(sighting));
        }
        return sightings;
    }

    private Hero getHeroForSightings(Sightings sightings) {
        final String GET_HERO_FOR_SIGHTINGS = "SELECT h.id, h.name, h.description, h.superpowerId "
                + "FROM Sightings si "
                + "JOIN Hero h ON si.heroId = h.id "
                + "WHERE si.id = ?";
        Hero superhero = jdbc.queryForObject(GET_HERO_FOR_SIGHTINGS, new HeroMapper(), sightings.getId());
        superhero.setPower(getPowerForHero(superhero.getId()));
        superhero.setOrganizations(getOrganizationsForHero(superhero.getId()));
        return superhero;
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

    private List<Organization> getOrganizationsForHero(int id) {
        final String sql = "SELECT o.id, o.name, o.description, o.address, o.contact "
                + "FROM OrganizationMember om "
                + "JOIN Organization o ON om.organizationId = o.id "
                + "WHERE om.heroId = ?";
        return jdbc.query(sql, new OrganizationMapper(), id);
    }

    private Location getLocationForSighting(Sightings sighting) {
        final String GET_LOCATION_FOR_SIGHTINGS = "SELECT l.id, l.name, l.description, l.address, l.latitude, l.longitude "
                + "FROM Sightings si "
                + "JOIN Location l ON si.locationId = l.id "
                + "WHERE si.id = ?";
        return jdbc.queryForObject(GET_LOCATION_FOR_SIGHTINGS, new LocationMapper(), sighting.getId());
    }

    @Override
    public List<Sightings> getLastTenSightings() {
        final String GET_LAST_TEN_SIGHTINGS = "SELECT * FROM Sightings ORDER BY date DESC, id DESC LIMIT 10";
        List<Sightings> sightings = jdbc.query(GET_LAST_TEN_SIGHTINGS, new SightingsMapper());
        for (Sightings sighting : sightings) {
            sighting.setSuperhero(getHeroForSightings(sighting));
            sighting.setLocation(getLocationForSighting(sighting));
        }
        return sightings;
    }

    public static final class SightingsMapper implements RowMapper<Sightings> {

        @Override
        public Sightings mapRow(ResultSet rs, int i) throws SQLException {
            Sightings sightings = new Sightings();
            sightings.setId(rs.getInt("id"));
            sightings.setDate(rs.getDate("date").toLocalDate());
            return sightings;
        }

    }
}
