package com.smart4aviation.airport.service;

import com.smart4aviation.airport.model.Cargo;
import com.smart4aviation.airport.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @InjectMocks
    private FlightService flightService;

    @Mock
    private FlightRepository flightRepository;

    @Test
    public void givenFlightNumberAndDateTime_returns_Cargo() {
        Integer flightNumber = 123;
        LocalDateTime dateTime = LocalDateTime.now();
        Cargo expectedCargo = new Cargo();

        when(flightRepository.findCargoByFlightNumberAndDate(flightNumber, dateTime))
                .thenReturn(expectedCargo);

        Cargo result = flightService.findCargoByFlightNumberAndDate(flightNumber, dateTime);
        assertEquals(expectedCargo, result);
    }

    @Test
    public void givenFlightNumberAndDateTime_returns_null() {
        Integer flightNumber = 456;
        LocalDateTime dateTime = LocalDateTime.now();

        when(flightRepository.findCargoByFlightNumberAndDate(flightNumber, dateTime))
                .thenReturn(null);

        Cargo result = flightService.findCargoByFlightNumberAndDate(flightNumber, dateTime);
        assertNull(result);
    }

}
