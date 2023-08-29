package com.smart4aviation.airport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Data
public class Cargo {

    @Id
    @Column(name = "cargo_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo_generator")
    @SequenceGenerator(name = "cargo_generator", sequenceName = "cargo_generator", allocationSize = 1)
    private Long cargoId;

//    @OneToMany(mappedBy = "cargo", fetch = FetchType.LAZY)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private List<Baggage> baggage;
//
//    @OneToMany(mappedBy = "cargo", fetch = FetchType.LAZY)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private List<CargoItem> cargoItems;

    @OneToMany (fetch = FetchType.LAZY)
    @JoinColumn(name = "baggage_id")
    @JsonIgnore
    private List<Baggage> baggage;

    @OneToMany (fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_item_id")
    @JsonIgnore
    private List<CargoItem> cargoItems;

}

