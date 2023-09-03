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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AirportRepositoryTest {

    @Autowired
    private AirportRepository airportRepository;


    @Autowired
    private FlightRepository flightRepository;

    Airport airport;

    Flight flight;

    @BeforeEach
    void setUp(){
        airport = new Airport();
        airport.setName("Airport1");
        airport.setIataAirportCode("ABC");
        airportRepository.save(airport);

        Cargo cargo = new Cargo();

        flight = new Flight();
        flight.setFlightNumber(123);
        flight.setDepartureAirport(airport);
        flight.setCargo(cargo);
        flightRepository.save(flight);
    }


    @DisplayName("JUnit saving a new airport")
    @Test
    public void save_should_add_airport(){
        Airport airport2 = new Airport();
        airport2.setName("Airport2");
        airport2.setIataAirportCode("ABCDE");
        airportRepository.save(airport2);

        Airport foundAirport = airportRepository.findById(airport2.getAirportId()).orElse(null);
        assertNotNull(foundAirport);
        assertEquals("Airport2", foundAirport.getName());
        assertEquals("ABCDE", foundAirport.getIataAirportCode());
    }


    @DisplayName("JUnit returning a list of departure flights from a given airport")
    @Test
    public void findById_return_departing_flights_list() {
        Cargo cargo2 = new Cargo();
        Cargo cargo3 = new Cargo();

        Flight flight2 = new Flight();
        flight2.setFlightNumber(123);
        flight2.setDepartureAirport(airport);
        flight2.setCargo(cargo2);

        Flight flight3 = new Flight();
        flight3.setFlightNumber(1234);
        flight3.setDepartureAirport(airport);
        flight3.setCargo(cargo3);

        flightRepository.save(flight2);
        flightRepository.save(flight3);

        List<Flight> departingFlights = new ArrayList<>();
        departingFlights.add(flight2);
        departingFlights.add(flight3);
        airport.setDepartingFlights(departingFlights);
        airportRepository.save(airport);

        Airport foundAirport = airportRepository.findById(airport.getAirportId()).orElse(null);
        assert foundAirport != null;
        List<Flight> foundDepartingFlights = foundAirport.getDepartingFlights();
        assertEquals(2, foundDepartingFlights.size());
    }

    @DisplayName("JUnit returning a list of arriving flights from a given airport")
    @Test
    public void findById_return_arriving_flights_list() {
        Cargo cargo2 = new Cargo();
        Cargo cargo3 = new Cargo();

        Flight flight2 = new Flight();
        flight2.setFlightNumber(123);
        flight2.setArrivalAirport(airport);
        flight2.setCargo(cargo2);

        Flight flight3 = new Flight();
        flight3.setFlightNumber(1234);
        flight3.setArrivalAirport(airport);
        flight3.setCargo(cargo3);

        flightRepository.save(flight2);
        flightRepository.save(flight3);

        List<Flight> arrivingFlights = new ArrayList<>();
        arrivingFlights.add(flight2);
        arrivingFlights.add(flight3);
        airport.setArrivingFlights(arrivingFlights);
        airportRepository.save(airport);

        Airport foundAirport = airportRepository.findById(airport.getAirportId()).orElse(null);
        assert foundAirport != null;
        List<Flight> foundArrivingFlights = foundAirport.getArrivingFlights();
        assertEquals(2, foundArrivingFlights.size());
    }

    @DisplayName("JUnit returning airport given airport iata code")
    @Test
    public void findAirportByIataAirportCode_return_airport() {
        Cargo cargo2 = new Cargo();
        Cargo cargo3 = new Cargo();

        Flight flight2 = new Flight();
        flight2.setFlightNumber(123);
        flight2.setArrivalAirport(airport);
        flight2.setCargo(cargo2);

        Flight flight3 = new Flight();
        flight3.setFlightNumber(1234);
        flight3.setArrivalAirport(airport);
        flight3.setCargo(cargo3);

        flightRepository.save(flight2);
        flightRepository.save(flight3);
        airportRepository.save(airport);
        Airport foundAirport = airportRepository.findAirportByIataAirportCode("ABC");

        assertNotNull(foundAirport);
        assertEquals("ABC", foundAirport.getIataAirportCode());
    }

    @DisplayName("JUnit deleting airport by id")
    @Test
    public void deleteById_remove_airport() {
        Airport airport3 = new Airport();
        airport3.setName("Airport3");
        airport3.setIataAirportCode("ABC-DEF");
        airportRepository.save(airport3);

        airportRepository.deleteById(airport3.getAirportId());
        Airport foundAirport = airportRepository.findById(airport3.getAirportId()).orElse(null);
        assertNull(foundAirport);
    }
}
