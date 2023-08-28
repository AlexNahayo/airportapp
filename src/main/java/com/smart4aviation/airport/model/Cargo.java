package com.smart4aviation.airport.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cargoId;

    @OneToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL)
    private List<Baggage> baggage;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL)
    private List<CargoItem> cargoItems;
}

