package com.smart4aviation.airport.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
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

    @OneToOne(mappedBy = "flight")
    private Cargo cargo;

}
