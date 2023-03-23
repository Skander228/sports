package com.example.sports.controllers;

import com.example.sports.models.Team;
import com.example.sports.models.TeamMembers;
import com.example.sports.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
