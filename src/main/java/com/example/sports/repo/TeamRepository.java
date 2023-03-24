package com.example.sports.repo;

import com.example.sports.models.Team;
import com.example.sports.models.TeamMembers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByOrderById();
}
