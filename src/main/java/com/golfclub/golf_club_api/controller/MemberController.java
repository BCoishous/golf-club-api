package com.golfclub.golf_club_api.controller;

import com.golfclub.golf_club_api.model.Member;
import com.golfclub.golf_club_api.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for Member endpoints.
 */
@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    // Add a new member
    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        Member saved = memberRepository.save(member);
        return ResponseEntity.ok(saved);
    }

    // Get all members
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberRepository.findAll());
    }

    // Get member by ID
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        return memberRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Search by name
    @GetMapping("/search/name")
    public ResponseEntity<List<Member>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(memberRepository.findByMemberNameContainingIgnoreCase(name));
    }

    // Search by membership type
    @GetMapping("/search/type")
    public ResponseEntity<List<Member>> searchByType(@RequestParam String type) {
        return ResponseEntity.ok(memberRepository.findByMembershipType(type));
    }

    // Search by phone number
    @GetMapping("/search/phone")
    public ResponseEntity<List<Member>> searchByPhone(@RequestParam String phone) {
        return ResponseEntity.ok(memberRepository.findByMemberPhone(phone));
    }

    // Search by tournament start date
    @GetMapping("/search/tournament-date")
    public ResponseEntity<List<Member>> searchByTournamentDate(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(memberRepository.findByTournaments_StartDate(localDate));
    }
}