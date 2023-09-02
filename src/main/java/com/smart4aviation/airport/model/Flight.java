package com.smart4aviation.airport.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "flight")
@Data
public class Flight {

    @Id
    @Column(name = "flight_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_generator")
    @SequenceGenerator(name = "flight_generator", sequenceName = "flight_generator", allocationSize = 1)
    private Long flightId;

    @Column(name = "flight_number")
    private Integer flightNumber;

    @Column(name = "departure_airport_iata_code")
    private String departureAirportIATACode;

    @Column(name = "arrival_airport_iata_code")
    private String arrivalAirportIATACode;

    @Column(name = "departure_date")
    private LocalDateTime departureDate;

    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cargo_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;
}
