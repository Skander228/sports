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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class TeamMembersController {
    @Autowired
    private TeamMembersRepository teamMembersRepository;

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/team_members")
    public String getTeamMembers(
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String nameSports,Model model) {

        Iterable<TeamMembers> teamMembers = null;
        Iterable<Team> team;

        if (role != null && !role.isEmpty()) {
            teamMembers = teamMembersRepository.findByRole(role);
        } else if (nameSports != null && !nameSports.isEmpty()) {
            team = teamRepository.findByNameSports(nameSports);
            Team t = team.iterator().next();
           teamMembers = teamMembersRepository.findByTeamId(t);
        } else {
            teamMembers = teamMembersRepository.findAll();
        }

        model.addAttribute("teamMembers", teamMembers);
        model.addAttribute("role", role);
        model.addAttribute("sports", nameSports);

        return "team_members";
    }

    @GetMapping("/create_team_members")
    public String createTeamMember(Model model) {
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

    @PostMapping("/remove_team_members/{id}")
    public String teamMembersDelete(
            @PathVariable(value = "id") Long id) {

        TeamMembers teamMembers = teamMembersRepository.findById(id).orElseThrow();
        teamMembersRepository.delete(teamMembers);

        return "redirect:/team_members";
    }

    @GetMapping("/edit_team_members/{id}")
    public String editTeamMembers(@PathVariable(value = "id") Long id, Model model) {
        if (!teamMembersRepository.existsById(id)) {
            return "redirect:/team_members";
        }

        Iterable<Team> teams = teamRepository.findAll();
        Optional<TeamMembers> teamMembers = teamMembersRepository.findById(id);
        ArrayList<TeamMembers> teamMembersList = new ArrayList<>();

        teamMembers.ifPresent(teamMembersList::add);

        model.addAttribute("teams", teams);
        model.addAttribute("teamMembers", teamMembersList);

        return "edit_team_members";
    }

    @PostMapping("/edit_team_members/{id}")
    public String editTeamMembersUpdate(
            @PathVariable(value = "id") Long id,
            @RequestParam Team teamId,
            @RequestParam String surname,
            @RequestParam String name,
            @RequestParam String patronymic,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth,
            @RequestParam String role) {

        if (teamId != null
                && surname != null && !surname.isEmpty()
                && name != null && !name.isEmpty()
                && patronymic != null && !patronymic.isEmpty()
                && dateOfBirth != null && !dateOfBirth.toString().isEmpty()
                && role != null && !role.isEmpty()) {

            TeamMembers teamMembers = teamMembersRepository.findById(id).orElseThrow();

            teamMembers.setTeamId(teamId);
            teamMembers.setSurname(surname);
            teamMembers.setName(name);
            teamMembers.setPatronymic(patronymic);
            teamMembers.setDateOfBirth(dateOfBirth);
            teamMembers.setRole(role);
            teamMembersRepository.save(teamMembers);
        } else {
            return "redirect:/edit_team_members/{id}";
        }
        return "redirect:/team_members";
    }
}
