package com.smart4aviation.airport.repository;


import com.smart4aviation.airport.model.Airport;
import com.smart4aviation.airport.model.Cargo;
import com.smart4aviation.airport.model.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FlightRepositoryTest {

    @Autowired
    private AirportRepository airportRepository;


    @Autowired
    private FlightRepository flightRepository;


    private final Airport airport = new Airport();

    private Flight flight;

    private Cargo cargo;

    @BeforeEach
    void setUp(){
        airport.setName("Airport1");
        airport.setIataAirportCode("ABC");
        airportRepository.save(airport);

        cargo = new Cargo();

        flight = new Flight();
        flight.setFlightNumber(123);
        flight.setDepartureAirport(airport);
        flight.setCargo(cargo);
        flight.setDepartureDate(LocalDateTime.of(2025, 5, 13, 15, 30));
        flightRepository.save(flight);

    }

    @DisplayName("JUnit finding a fight by id ")
    @Test
    public void findById_will_return_flight() {
        Flight retrievedFlight = flightRepository.findById(flight.getFlightId()).orElse(null);
        assertNotNull(retrievedFlight);
        assertEquals(airport, retrievedFlight.getDepartureAirport());
    }

    @DisplayName("JUnit for deleting a flight from the airport")
    @Test
    public void deleteById_will_delete_flight() {
        flightRepository.deleteById(flight.getFlightId());
        assertEquals(Optional.empty(), flightRepository.findById(flight.getFlightId()));
    }


    @DisplayName("JUnit retrieving cargo from flight id")
    @Test
    public void findCargoByFlightId_will_return_cargo(){
        Cargo cargoAssociatedWithFlight = flightRepository.findCargoByFlightId(flight.getCargo().getCargoId());
        assertEquals(cargo, cargoAssociatedWithFlight);
    }

    @DisplayName("JUnit retrieving cargo from flight number")
    @Test
    public void findCargoByFlightNumber_will_return_cargo(){
        Cargo cargoAssociatedWithFlight = flightRepository.findCargoByFlightNumber(123);
        assertEquals(cargo, cargoAssociatedWithFlight);
    }

    @DisplayName("JUnit retrieving cargo from flight number and Date")
    @Test
    public void findCargoByFlightNumberAndDate_will_return_cargo(){
        LocalDateTime  flightDepartureDateAndTime = LocalDateTime.of(2025, 5, 13, 15, 30);
        Cargo cargoAssociatedWithFlight = flightRepository.findCargoByFlightNumberAndDate(123, flightDepartureDateAndTime);
        assertEquals(cargo, cargoAssociatedWithFlight);
    }

}
