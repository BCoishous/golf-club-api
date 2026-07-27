package com.golfclub.golf_club_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a golf tournament.
 * Maps to the "tournaments" table in the database.
 */
@Entity
@Table(name = "tournaments")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "entry_fee")
    private Double entryFee;

    @Column(name = "cash_prize")
    private Double cashPrize;

    @ManyToMany
    @JoinTable(
        name = "tournament_members",
        joinColumns = @JoinColumn(name = "tournament_id"),
        inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private Set<Member> participatingMembers = new HashSet<>();

    // Constructors
    public Tournament() {}

    public Tournament(LocalDate startDate, LocalDate endDate, String location,
                      Double entryFee, Double cashPrize) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.entryFee = entryFee;
        this.cashPrize = cashPrize;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Double getEntryFee() { return entryFee; }
    public void setEntryFee(Double entryFee) { this.entryFee = entryFee; }

    public Double getCashPrize() { return cashPrize; }
    public void setCashPrize(Double cashPrize) { this.cashPrize = cashPrize; }

    public Set<Member> getParticipatingMembers() { return participatingMembers; }
    public void setParticipatingMembers(Set<Member> participatingMembers) { this.participatingMembers = participatingMembers; }
}