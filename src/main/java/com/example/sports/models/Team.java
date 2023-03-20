package com.example.sports.models;

import jakarta.persistence.*;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameTeam;
    private String kindOfSport;
    private String date;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getNameTeam() {return nameTeam;}
    public void setNameTeam(String nameTeam) {this.nameTeam = nameTeam;}
    public String getKindOfSport() {return kindOfSport;}
    public void setKindOfSport(String kindOfSport) {this.kindOfSport = kindOfSport;}
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}

}
