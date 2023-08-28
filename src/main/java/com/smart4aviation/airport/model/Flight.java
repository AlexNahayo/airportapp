package com.smart4aviation.airport.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long flightId;

    @Column(name = "flightNumber")
    private Integer flightNumber;

    @Column(name = "departureAirportIATACode")
    private String departureAirportIATACode;

    @Column(name = "arrivalAirportIATACode")
    private String arrivalAirportIATACode;

    @Column(name = "departureDate")
    private LocalDateTime departureDate;

}
