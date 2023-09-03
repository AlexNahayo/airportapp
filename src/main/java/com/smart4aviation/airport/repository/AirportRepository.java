package com.smart4aviation.airport.repository;

import com.smart4aviation.airport.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Airport findAirportByIataAirportCode(String airportCode);

}
