package com.golfclub.golf_club_api.repository;

import com.golfclub.golf_club_api.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository for Member database operations.
 * Spring Data JPA auto-implements all these methods — no SQL needed.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // Search by name (partial match, case insensitive)
    List<Member> findByMemberNameContainingIgnoreCase(String name);

    // Search by membership type
    List<Member> findByMembershipType(String membershipType);

    // Search by phone number
    List<Member> findByMemberPhone(String memberPhone);

    // Search by tournament start date
    List<Member> findByTournaments_StartDate(LocalDate startDate);
}