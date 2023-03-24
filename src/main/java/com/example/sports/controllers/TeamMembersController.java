package com.example.sports.controllers;

import com.example.sports.models.Team;
import com.example.sports.models.TeamMembers;
import com.example.sports.repo.TeamMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Set;

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

    @GetMapping("/create_team_members")
    private String createTeamNumber(Model model) {
        Iterable<TeamMembers> teamMembers = teamMembersRepository.findAllByOrderByTeamId();
        model.addAttribute("team_members", teamMembers.toString());
        return "create_team_members";
    }

    @PostMapping("/create_team_members")
    private String createTeamNumber(@RequestParam Team teamId,
                                    @RequestParam String surname,
                                    @RequestParam String name,
                                    @RequestParam String patronymic,
                                    @RequestParam Date dateOfBirth,
                                    @RequestParam String role, Model model) {
        TeamMembers teamMembers = new TeamMembers(teamId, surname, name, patronymic, dateOfBirth, role);
        teamMembersRepository.save(teamMembers);
        return "create_team_members";
    }
}
