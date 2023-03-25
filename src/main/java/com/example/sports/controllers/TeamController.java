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
import java.util.List;

@Controller
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/team")
    public String getTeam(Model model) {
        Iterable<Team> team = teamRepository.findAll();
        model.addAttribute("team",team);
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
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, Model model) {
        Team team = new Team(nameTeam, nameSports, date);
        teamRepository.save(team);
        return "redirect:/team";
    }

    @PostMapping("/team_filter_sports")
    public String filterSports(@RequestParam String nameSports, Model model) {
        Iterable<Team> filterNameSports;

        if (nameSports != null && !nameSports.isEmpty()) {
            filterNameSports = teamRepository.findByNameSports(nameSports);
        } else {
            filterNameSports = teamRepository.findAll();
        }

        model.addAttribute("team", filterNameSports);
        return "team";
    }


}
