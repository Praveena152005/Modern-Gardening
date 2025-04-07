package com.examly.springapp.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long bookingid;
    Long userid;
    Long gardenerid;
    LocalDate date;
    LocalTime time;
    String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-booking")
    private User user;

    @ManyToOne
    @JoinColumn(name = "gardener_id")
    @JsonBackReference("gardener-booking")
    private Gardener gardener;

}
