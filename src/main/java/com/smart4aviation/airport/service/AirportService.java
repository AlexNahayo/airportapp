package com.smart4aviation.airport.service;

import com.smart4aviation.airport.model.Airport;
import com.smart4aviation.airport.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

    @Autowired
    AirportRepository airportRepository;
    public Airport airportByIataAirportCode(String iataAirport){
        return airportRepository.findAirportByIataAirportCode(iataAirport);
    }
}
