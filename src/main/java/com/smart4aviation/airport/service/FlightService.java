package com.smart4aviation.airport.service;

import com.smart4aviation.airport.model.Cargo;
import com.smart4aviation.airport.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    public Cargo findCargoByFlightNumberAndDate(Integer flightNumber, LocalDateTime dateTime){
        return flightRepository.findCargoByFlightNumberAndDate(flightNumber, dateTime);
    }

}
