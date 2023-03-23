package com.example.sports.models;

import jakarta.persistence.*;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name_team", unique = true, nullable = false)
    private String nameTeam;
    @Column(name = "name_sports", nullable = false)
    private String nameSports;
    @Column(name = "date")
    private String date;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getNameTeam() {return nameTeam;}
    public void setNameTeam(String nameTeam) {this.nameTeam = nameTeam;}
    public String getNameSports() {return nameSports;}
    public void setNameSports(String nameSports) {this.nameSports = nameSports;}
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}

    public Team() {};
    public Team(String nameTeam, String nameSports, String date) {
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
