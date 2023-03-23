package com.example.sports.controllers;

import com.example.sports.models.Team;
import com.example.sports.models.TeamMembers;
import com.example.sports.repo.TeamMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeamMembersController {
    @Autowired
    private TeamMembersRepository teamMembersRepository;

    @GetMapping("/team_members")
    private String getTeamMembers(Model model) {
        Iterable<TeamMembers> teamMembers = teamMembersRepository.findAll();
        model.addAttribute("teamMembers",teamMembers);
        return "team_members";
    }
}
