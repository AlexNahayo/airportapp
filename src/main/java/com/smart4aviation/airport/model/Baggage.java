package com.smart4aviation.airport.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Baggage {

    @Id
    @Column(name = "baggage_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "baggage_generator")
    @SequenceGenerator(name = "baggage_generator", sequenceName = "baggage_generator", allocationSize = 1)
    private Long BaggageId;

    @Column(name = "baggage_weight")
    private Integer weight;

    @Column(name = "weight_unit")
    private String weightUnit;

    @Column(name = "baggage_pieces")
    private Integer pieces;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;
}

