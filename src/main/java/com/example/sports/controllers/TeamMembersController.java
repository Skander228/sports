package com.example.sports.controllers;

import com.example.sports.models.Team;
import com.example.sports.models.TeamMembers;
import com.example.sports.repo.TeamMembersRepository;
import com.example.sports.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/team_members")
    private String getTeamMembers(Model model) {
        Iterable<TeamMembers> teamMembers = teamMembersRepository.findAll();
        model.addAttribute("teamMembers",teamMembers);
        return "team_members";
    }

    @GetMapping("/create_team_members")
    public String createTeamNumber(Model model) {
        Iterable<Team> team = teamRepository.findAll();
        model.addAttribute("team", team);
        return "create_team_members";
    }

    @PostMapping("/create_team_members")
    public String createTeamNumber(@RequestParam Team teamId,
                                   @RequestParam String surname,
                                   @RequestParam String name,
                                   @RequestParam String patronymic,
                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")  Date dateOfBirth,
                                   @RequestParam String role, Model model) {

        TeamMembers teamMembers = new TeamMembers(teamId, surname, name, patronymic, dateOfBirth, role);
        teamMembersRepository.save(teamMembers);

        return "redirect:/team_members";
    }
}
