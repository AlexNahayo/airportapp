package com.smart4aviation.airport.repository;

import com.smart4aviation.airport.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
