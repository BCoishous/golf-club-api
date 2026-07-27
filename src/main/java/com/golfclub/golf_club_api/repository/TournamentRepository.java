package com.golfclub.golf_club_api.repository;

import com.golfclub.golf_club_api.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository for Tournament database operations.
 * Spring Data JPA auto-implements all these methods — no SQL needed.
 */
@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    // Search by start date
    List<Tournament> findByStartDate(LocalDate startDate);

    // Search by location (partial match, case insensitive)
    List<Tournament> findByLocationContainingIgnoreCase(String location);
}