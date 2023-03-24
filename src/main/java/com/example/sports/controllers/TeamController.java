package com.example.sports.controllers;

import com.example.sports.models.Team;
import com.example.sports.models.TeamMembers;
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
    private String getTeam(Model model) {
        Iterable<Team> team = teamRepository.findAll();
        model.addAttribute("team",team);
        return "team";
    }

    @GetMapping("/create_team")
    private String createTeam(Model model) {
        return "create_team";
    }

    @PostMapping("/create_team")
    private String createTeam(@RequestParam String nameTeam,
                              @RequestParam String nameSports,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, Model model) {
        Team team = new Team(nameTeam, nameSports, date);
        teamRepository.save(team);
        return "redirect:/team";
    }

}
