/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Sightings;
import com.sg.superherosightings.service.HeroService;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ivaylomaslev
 */
@Controller
public class SightingsController {

    @Autowired
    HeroService service;

    Set<ConstraintViolation<Sightings>> violations = new HashSet<>();

    @GetMapping("/")
    public String displayLastTenSightings(Model model) {
        List<Sightings> sightings = service.getLastTenSightings();
        model.addAttribute("sightings", sightings);
        return "index";
    }

    @GetMapping("sightings")
    public String displaySightings(Model model) {
        List<Sightings> sightings = service.getAllSightings();
        List<Hero> heros = service.getAllHeros();
        List<Location> locations = service.getAllLocations();
        LocalDate now = LocalDate.now();
        model.addAttribute("sightings", sightings);
        model.addAttribute("heros", heros);
        model.addAttribute("locations", locations);
        model.addAttribute("now", now);
        model.addAttribute("errors", violations);
        return "sightings";
    }

    @GetMapping("sightingsByDate")
    public String displaySightingsByDate(String date, Model model) {
        LocalDate sightingDate = LocalDate.parse(date);
        List<Sightings> sightings = service.getSightingsByDate(sightingDate);
        List<Hero> heros = service.getAllHeros();
        List<Location> locations = service.getAllLocations();
        LocalDate now = LocalDate.now();
        model.addAttribute("sightings", sightings);
        model.addAttribute("heros", heros);
        model.addAttribute("locations", locations);
        model.addAttribute("now", now);
        model.addAttribute("errors", violations);
        return "sightings";
    }

    @PostMapping("addSighting")
    public String addSightings(String heroId, String locationId, String date, HttpServletRequest request) {
        Hero hero = service.getHeroById(Integer.parseInt(heroId));
        Location location = service.getLocationById(Integer.parseInt(locationId));
        LocalDate sightingDate = LocalDate.parse(date);

        Sightings sightings = new Sightings();
        sightings.setSuperhero(hero);
        sightings.setLocation(location);
        sightings.setDate(sightingDate);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(sightings);

        if (violations.isEmpty()) {
            service.addSightings(sightings);
        }

        return "redirect:/sighting";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(Integer id, Model model) {
        service.deleteSightingsById(id);
        return "redirect:/sightings";
    }

    @GetMapping("editSightings")
    public String editSightings(Integer id, Model model) {
        Sightings sightings = service.getSightingsById(id);
        List<Hero> heros = service.getAllHeros();
        List<Location> locations = service.getAllLocations();
        LocalDate now = LocalDate.now();
        model.addAttribute("sightings", sightings);
        model.addAttribute("heros", heros);
        model.addAttribute("locations", locations);
        model.addAttribute("now", now);
        return "editSightings";
    }

    @PostMapping("editSightings")
    public String performEditSightings(String id, String heroId, String locationId, String date, HttpServletRequest request) {
        Hero hero = service.getHeroById(Integer.parseInt(heroId));
        Location location = service.getLocationById(Integer.parseInt(locationId));
        LocalDate sightingsDate = LocalDate.parse(date);

        Sightings sightings = new Sightings();
        sightings.setId(Integer.parseInt(id));
        sightings.setSuperhero(hero);
        sightings.setLocation(location);
        sightings.setDate(sightingsDate);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(sightings);

        if (violations.isEmpty()) {
            service.updateSightings(sightings);
        }

        return "redirect:/sightings";
    }

}
