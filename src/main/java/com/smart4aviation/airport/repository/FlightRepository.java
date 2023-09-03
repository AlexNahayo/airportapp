package com.smart4aviation.airport.repository;

import com.smart4aviation.airport.model.Cargo;
import com.smart4aviation.airport.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f.cargo FROM Flight f WHERE f.flightId = :flightId")
    Cargo findCargoByFlightId(@Param("flightId") Long flightId);

    @Query("SELECT f.cargo FROM Flight f WHERE f.flightNumber = :flightNumber")
    Cargo findCargoByFlightNumber(@Param("flightNumber") Integer flightNumber);

    @Query("SELECT f.cargo FROM Flight f WHERE f.flightNumber = :flightNumber AND f.departureDate = :departureDate")
    Cargo findCargoByFlightNumberAndDate(
            @Param("flightNumber") Integer flightNumber,
            @Param("departureDate") LocalDateTime departureDate
    );

}
