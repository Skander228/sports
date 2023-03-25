package com.example.sports.repo;

import com.example.sports.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByNameSports(String nameSports);
    List<Team> 	findByDateGreaterThan(Date date);
}
