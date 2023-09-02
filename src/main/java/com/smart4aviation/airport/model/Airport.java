package com.smart4aviation.airport.model;

import lombok.Data;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airport")
@Data
public class Airport {

    @Id
    @Column(name = "airport_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "airport_generator")
    @SequenceGenerator(name = "airport_generator", sequenceName = "airport_generator", allocationSize = 1)
    private Long airportId;

    @Column(name = "iata_airport_code")
    private String IATAAirportCode;

    @Column(name = "airport_name")
    private String name;

    @OneToMany(mappedBy = "departureAirport", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Flight> departingFlights = new ArrayList<>();

    @OneToMany(mappedBy = "arrivalAirport", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Flight> arrivingFlights = new ArrayList<>();

}

