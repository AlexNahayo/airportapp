package com.smart4aviation.airport.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "cargo_item")
@Data
public class CargoItem {

    @Id
    @Column(name = "cargo_item_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo_item_generator")
    @SequenceGenerator(name = "cargo_item_generator", sequenceName = "cargo_item_generator", allocationSize = 1)
    private Long CargoItemId;

    @Column(name = "cargo_item_weight")
    private Integer weight;

    @Column(name = "cargo_item_weight_unit")
    private String weightUnit;

    @Column(name = "cargo_item_baggage_pieces")
    private Integer pieces;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

}
