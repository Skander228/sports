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
    public String createTeamNumber(
            @RequestParam(required = false) Team teamId,
            @RequestParam String surname,
            @RequestParam String name,
            @RequestParam String patronymic,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")  Date dateOfBirth,
            @RequestParam String role, Model model) {

        if (teamId != null && !teamId.toString().isEmpty()
                && surname != null && !surname.isEmpty()
                && name != null && !name.isEmpty()
                && patronymic != null && !patronymic.isEmpty()
                && dateOfBirth != null && !dateOfBirth.toString().isEmpty()) {

            TeamMembers teamMembers = new TeamMembers(teamId, surname, name, patronymic, dateOfBirth, role);
            teamMembersRepository.save(teamMembers);
        } else {
            return "redirect:/create_team_members";
        }



        return "redirect:/team_members";
    }

    @PostMapping("/filter_teamMembers_role")
    public String filterRole(@RequestParam String role, Model model) {
        Iterable<TeamMembers> filterRole;

        if (role != null && !role.isEmpty()) {
            filterRole = teamMembersRepository.findByRole(role);
        } else {
            filterRole = teamMembersRepository.findAll();
        }

        model.addAttribute("teamMembers", filterRole);
        return "team_members";
    }

    /*@PostMapping("/filter_teamMembers_sports")
    public String filterSports(@RequestParam String nameSports, Model model) {
        Iterable<TeamMembers> filterNameSports;

        if (nameSports != null && !nameSports.isEmpty()) {
            filterNameSports = teamMembersRepository.findByNameSports(nameSports);
        } else {
            filterNameSports = teamMembersRepository.findAll();
        }

        model.addAttribute("teamMembers", filterNameSports);
        return "team_members";
    }*/

/*    @PostMapping("/create_team_members")
    public String editTeamNumber(@RequestParam Team teamId,
                                 @RequestParam String surname,
                                 @RequestParam String name,
                                 @RequestParam String patronymic,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")  Date dateOfBirth,
                                 @RequestParam String role, Model model) {

        TeamMembers teamMembers = new TeamMembers(teamId, surname, name, patronymic, dateOfBirth, role);
        teamMembersRepository.save(teamMembers);

        return "redirect:/team_members";
    }*/
}
