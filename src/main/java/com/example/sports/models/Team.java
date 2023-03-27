package com.example.sports.models;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teamId", cascade = CascadeType.REMOVE)
    private List<TeamMembers> teamMembers = new ArrayList<>();
    @Column(name = "name_team", nullable = false)
    private String nameTeam;
    @Column(name = "name_sports", nullable = false)
    private String nameSports;
    @Column(name = "date", nullable = false)
    private Date date;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public List<TeamMembers> getTeamMembers() {return teamMembers;}
    public void setTeamMembers(List<TeamMembers> teamMembers) {this.teamMembers = teamMembers;}
    public String getNameTeam() {return nameTeam;}
    public void setNameTeam(String nameTeam) {this.nameTeam = nameTeam;}
    public String getNameSports() {return nameSports;}
    public void setNameSports(String nameSports) {this.nameSports = nameSports;}
    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}

    public Team() {};
    public Team(String nameTeam, String nameSports, Date date) {
        this.nameTeam = nameTeam;
        this.nameSports = nameSports;
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("Item [id = %d, nameTeam = '%s', nameSports = '%s'," +
                " date = '%s']", id, nameTeam, nameSports, date);
    }
}
