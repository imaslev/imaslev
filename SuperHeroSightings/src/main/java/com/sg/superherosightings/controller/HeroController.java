/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Superpower;
import com.sg.superherosightings.service.HeroService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
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
public class HeroController {

    @Autowired
    HeroService service;

    Set<ConstraintViolation<Hero>> violations = new HashSet<>();

    @GetMapping("hero")
    public String displayHeros(Model model) {
        List<Hero> heros = service.getAllHeros();
        List<Superpower> superpowers = service.getAllSuperpowers();
        List<Organization> orgs = service.getAllOrgs();
        model.addAttribute("heros", heros);
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("orgs", orgs);
        model.addAttribute("errors", violations);
        return "hero";
    }

    @PostMapping("addHero")
    public String addHero(Hero hero, HttpServletRequest request) {
        String superpowerId = request.getParameter("superpowerId");
        String[] orgIds = request.getParameterValues("organizationId");

        if (superpowerId.equals("No Superpower")) {
            hero.setPower(null);
        } else {
            hero.setPower(service.getSuperpowerById(Integer.parseInt(superpowerId)));
        }

        List<Organization> orgs = new ArrayList<Organization>();
        if (orgIds != null && !Arrays.stream(orgIds).anyMatch("No Organization"::equals)) {
            for (String id : orgIds) {
                orgs.add(service.getOrgById(Integer.parseInt(id)));
            }
        }
        hero.setOrganizations(orgs);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(hero);

        if (violations.isEmpty()) {
            service.addHero(hero);
        }

        return "redirect:/hero";
    }

    @GetMapping("heroDetails")
    public String heroDetails(Integer id, Model model) {
        Hero hero = service.getHeroById(id);
        List<Location> locations = service.getLocationsByHero(hero);
        model.addAttribute("hero", hero);
        model.addAttribute("locations", locations);
        return "heroDetails";
    }

    @GetMapping("deleteHero")
    public String deleteHero(Integer id, Model model) {
        service.deleteHeroById(id);
        return "redirect:/hero";
    }

    @GetMapping("editHero")
    public String editHero(Integer id, Model model) {
        Hero hero = service.getHeroById(id);
        List<Superpower> superpowers = service.getAllSuperpowers();
        List<Organization> orgs = service.getAllOrgs();
        model.addAttribute("hero", hero);
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("orgs", orgs);
        return "editHero";
    }

    @PostMapping("editHero")
    public String performEditHero(@Valid Hero hero, HttpServletRequest request) {
        String superpowerId = request.getParameter("superpowerId");
        String[] orgIds = request.getParameterValues("organizationId");

        if (superpowerId.equals("No Superpower")) {
            hero.setPower(null);
        } else {
            hero.setPower(service.getSuperpowerById(Integer.parseInt(superpowerId)));
        }

        List<Organization> orgs = new ArrayList<Organization>();
        if (orgIds != null && !Arrays.stream(orgIds).anyMatch("No Organization"::equals)) {
            for (String id : orgIds) {
                orgs.add(service.getOrgById(Integer.parseInt(id)));
            }
        }
        hero.setOrganizations(orgs);

        service.updateHero(hero);
        return "redirect:/hero";
    }

}
