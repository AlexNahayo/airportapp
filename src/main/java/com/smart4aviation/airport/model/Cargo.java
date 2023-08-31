package com.smart4aviation.airport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cargo")
@Data
public class Cargo {

    @Id
    @Column(name = "cargo_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo_generator")
    @SequenceGenerator(name = "cargo_generator", sequenceName = "cargo_generator", allocationSize = 1)
    private Long cargoId;

    @OneToMany(mappedBy = "cargo", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Baggage> baggage = new ArrayList<>();

    @OneToMany(mappedBy = "cargo", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<CargoItem> cargoItems = new ArrayList<>();
}



