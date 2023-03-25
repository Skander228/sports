package com.example.sports.controllers;

import com.example.sports.models.Team;
import com.example.sports.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/team")
    public String getTeam(
            @RequestParam(required = false) String nameSports,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            Model model) {
        Iterable<Team> team;

        if (nameSports != null && !nameSports.isEmpty()) {
            team = teamRepository.findByNameSports(nameSports);
        } else if (date != null && !date.toString().isEmpty()) {
            team = teamRepository.findByDateGreaterThan(date);
        } else {
            team = teamRepository.findAll();
        }

        model.addAttribute("team", team);
        model.addAttribute("nameSports", nameSports);

        return "team";
    }

    @GetMapping("/create_team")
    public String createTeam(Model model) {
        return "create_team";
    }

    @PostMapping("/create_team")
    public String createTeam(
            @RequestParam String nameTeam,
            @RequestParam String nameSports,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, Model model) {
        if (date != null && !date.toString().isEmpty()
                && nameSports != null && !nameSports.isEmpty()
                && nameTeam != null && !nameTeam.isEmpty()) {
            Team team = new Team(nameTeam, nameSports, date);
            teamRepository.save(team);
        } else {
            return "create_team";
        }
        return "redirect:/team";
    }

}
