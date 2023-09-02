package com.smart4aviation.airport.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SampleModelClassesTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void generateSampleData() {

        Airport airport = new Airport();
        airport.setIATAAirportCode("JFK");
        airport.setName("John F. Kennedy International Airport");
        entityManager.persist(airport);

        Airport airport2 = new Airport();
        airport2.setIATAAirportCode("DUB");
        airport2.setName("Dublin Airport");

        Cargo cargo = new Cargo();
        entityManager.persist(cargo);

        Baggage baggage = new Baggage();
        baggage.setWeight(20);
        baggage.setWeightUnit("kg");
        baggage.setPieces(2);
        baggage.setCargo(cargo);
        entityManager.persist(baggage);

        CargoItem cargoItem = new CargoItem();
        cargoItem.setWeight(30);
        cargoItem.setWeightUnit("kg");
        cargoItem.setPieces(3);
        cargoItem.setCargo(cargo);
        entityManager.persist(cargoItem);

        Flight flight = new Flight();
        flight.setFlightNumber(1236);
        flight.setDepartureAirportIATACode("JFK");
        flight.setArrivalAirportIATACode("DUB");
        flight.setDepartureDate(LocalDateTime.now());
        flight.setArrivalDate(LocalDateTime.now().plusHours(5));
        flight.setCargo(cargo);
        flight.setDepartureAirport(airport);
        flight.setArrivalAirport(airport2);
        entityManager.persist(flight);

        Airport foundAirport = entityManager.find(Airport.class, airport.getAirportId());
        assertNotNull(foundAirport);
        assertEquals("JFK", foundAirport.getIATAAirportCode());

        Cargo foundCargo = entityManager.find(Cargo.class, cargo.getCargoId());
        assertNotNull(foundCargo);

        Baggage foundBaggage = entityManager.find(Baggage.class, baggage.getBaggageId());
        assertNotNull(foundBaggage);
        assertEquals(20, foundBaggage.getWeight());
        assertEquals("kg", foundBaggage.getWeightUnit());
        assertEquals(foundCargo.getCargoId(), foundBaggage.getCargo().getCargoId());

        CargoItem foundcargoItem = entityManager.find(CargoItem.class, cargoItem.getCargoItemId());
        assertNotNull(foundcargoItem);
        assertEquals(30, foundcargoItem.getWeight());
        assertEquals(3, foundcargoItem.getPieces());
        assertEquals(foundCargo.getCargoId(), foundBaggage.getCargo().getCargoId());


        Flight foundFlight = entityManager.find(Flight.class, flight.getFlightId());
        assertNotNull(foundAirport);
        assertEquals("DUB", foundFlight.getArrivalAirportIATACode());
        assertEquals(1236, foundFlight.getFlightNumber());
        assertEquals(foundFlight.getDepartureAirport().getAirportId(), foundAirport.getAirportId());
        assertEquals(foundFlight.getCargo().getCargoId(), foundCargo.getCargoId());

    }
}

