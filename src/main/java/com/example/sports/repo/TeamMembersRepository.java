package com.example.sports.repo;

import com.example.sports.models.TeamMembers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMembersRepository extends JpaRepository<TeamMembers, Long> {

}
