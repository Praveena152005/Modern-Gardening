package com.examly.springapp.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "diy_tutorials")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DIYTutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long tutorialid;
    String title;
    String description;
    String vediourl;
    
    @ManyToOne
    @JoinColumn(name = "gardener_id")
    @JsonBackReference("gardener-diy")
    private Gardener gardener;
}
