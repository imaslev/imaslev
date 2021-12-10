/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Organization;
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
public class OrganizationsController {

    @Autowired
    HeroService service;

    Set<ConstraintViolation<Organization>> violations = new HashSet<>();

    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> orgs = service.getAllOrgs();
        List<Hero> heros = service.getAllHeros();
        model.addAttribute("orgs", orgs);
        model.addAttribute("heros", heros);
        model.addAttribute("errors", violations); 
        return "organizations";
    }

    @PostMapping("addOrganization")
    public String addOrganization(Organization org, HttpServletRequest request) {
        String[] heroIds = request.getParameterValues("heroId");

        List<Hero> heros = new ArrayList<Hero>();
        if (heroIds != null && !Arrays.stream(heroIds).anyMatch("No Hero"::equals)) {
            for (String id : heroIds) {
                heros.add(service.getHeroById(Integer.parseInt(id)));
            }
        }
        org.setHeros(heros);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(org);

        if (violations.isEmpty()) {
            service.addOrg(org);
        }

        return "redirect:/organizations";
    }

    @GetMapping("organizationDetails")
    public String organizationDetails(Integer id, Model model) {
        Organization org = service.getOrgById(id);
        model.addAttribute("org", org);
        return "organizationDetails";
    }

    @GetMapping("deleteOrganization")
    public String deleteOrganization(Integer id, Model model) {
        service.deleteOrgById(id);
        return "redirect:/organizations";
    }

    @GetMapping("editOrganization")
    public String editOrganization(Integer id, Model model) {
        Organization org = service.getOrgById(id);
        List<Hero> heros = service.getAllHeros();
        model.addAttribute("org", org);
        model.addAttribute("heros", heros);
        return "editOrganizations";
    }

    @PostMapping("editOrganizations")
    public String performEditOrganization(@Valid Organization org, HttpServletRequest request) {
        String[] heroIds = request.getParameterValues("heroId");

        List<Hero> heros = new ArrayList<Hero>();
        if (heroIds != null && !Arrays.stream(heroIds).anyMatch("No Heros"::equals)) {
            for (String id : heroIds) {
                heros.add(service.getHeroById(Integer.parseInt(id)));
            }
        }
        org.setHeros(heros);

        service.updateOrg(org);
        return "redirect:/organizations";
    }

}
