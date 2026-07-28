package com.golfclub.golf_club_api.controller;

import com.golfclub.golf_club_api.model.Member;
import com.golfclub.golf_club_api.model.Tournament;
import com.golfclub.golf_club_api.repository.MemberRepository;
import com.golfclub.golf_club_api.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for Tournament endpoints.
 */
@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private MemberRepository memberRepository;

    // Add a new tournament
    @PostMapping
    public ResponseEntity<Tournament> addTournament(@RequestBody Tournament tournament) {
        Tournament saved = tournamentRepository.save(tournament);
        return ResponseEntity.ok(saved);
    }

    // Get all tournaments
    @GetMapping
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        return ResponseEntity.ok(tournamentRepository.findAll());
    }

    // Get tournament by ID
    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
        return tournamentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Search by start date
    @GetMapping("/search/date")
    public ResponseEntity<List<Tournament>> searchByDate(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(tournamentRepository.findByStartDate(localDate));
    }

    // Search by location
    @GetMapping("/search/location")
    public ResponseEntity<List<Tournament>> searchByLocation(@RequestParam String location) {
        return ResponseEntity.ok(tournamentRepository.findByLocationContainingIgnoreCase(location));
    }

    // Register a member to a tournament
    @PostMapping("/{tournamentId}/members/{memberId}")
    public ResponseEntity<Tournament> registerMember(
            @PathVariable Long tournamentId,
            @PathVariable Long memberId) {

        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElse(null);
        Member member = memberRepository.findById(memberId)
                .orElse(null);

        if (tournament == null || member == null) {
            return ResponseEntity.notFound().build();
        }

        tournament.getParticipatingMembers().add(member);
        tournamentRepository.save(tournament);
        return ResponseEntity.ok(tournament);
    }
}