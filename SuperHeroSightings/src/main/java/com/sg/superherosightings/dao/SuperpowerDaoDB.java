/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Hero;
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
public class SuperpowerDaoDB implements SuperpowerDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Superpower getPowerById(int id) {
        try {
            final String sql = "SELECT * FROM Superpower WHERE id = ?";
            return jdbc.queryForObject(sql, new PowerMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superpower> getAllPowers() {
        final String sql = "SELECT * FROM Superpower";
        return jdbc.query(sql, new PowerMapper());
    }

    @Override
    @Transactional
    public Superpower addPower(Superpower power) {
        final String sql = "INSERT INTO Superpower(name) VALUES (?)";
        jdbc.update(sql, power.getName());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        power.setId(newId);
        return power;
    }

    @Override
    @Transactional
    public void updatePower(Superpower power) {
        final String sql = "UPDATE Superpower SET name = ? WHERE id = ?";
        jdbc.update(sql, power.getName(), power.getId());
    }

    @Override
    @Transactional
    public void deletePowerById(int id) {
        final String UPDATE_SUPERPOWER = "UPDATE Hero SET superpowerId = null WHERE superpowerId = ?";
        jdbc.update(UPDATE_SUPERPOWER, id);

        final String DELETE_SUPERPOWER = "DELETE FROM Superpower WHERE id = ?";
        jdbc.update(DELETE_SUPERPOWER, id);
    }

    @Override
    public Superpower getPowerByHero(Hero superhero) {
        try {
            final String sql = "SELECT * FROM Superpower WHERE id = ?";
            return jdbc.queryForObject(sql, new PowerMapper(), superhero.getPower().getId());
        } catch (DataAccessException ex) {
            return null;
        }
    }

    public static final class PowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int i) throws SQLException {
            Superpower power = new Superpower();
            power.setId(rs.getInt("id"));
            power.setName(rs.getString("name"));
            return power;
        }

    }

}
