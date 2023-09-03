package com.smart4aviation.airport.controller;

import com.smart4aviation.airport.controller.AirportController;
import com.smart4aviation.airport.model.Airport;
import com.smart4aviation.airport.model.Cargo;
import com.smart4aviation.airport.model.Flight;
import com.smart4aviation.airport.service.AirportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(AirportController.class)
public class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirportService airportService;

    @Test
    public void testGetAirportInfo() throws Exception {
        String airportCode = "ABC";
        LocalDateTime dateTime = LocalDateTime.of(2025, 5, 13, 18, 30);
        Airport airport = new Airport();

        List<Flight> departingFlights = new ArrayList<>();
        Cargo cargo = new Cargo();
        Flight departingFlight = new Flight();
        departingFlight.setDepartureDate(dateTime);
        departingFlight.setCargo(cargo);
        departingFlights.add(departingFlight);

        List<Flight> arrivingFlights = new ArrayList<>();
        Cargo cargo2 = new Cargo();
        Flight arrivingFlight = new Flight();
        arrivingFlight.setArrivalDate(dateTime);
        arrivingFlight.setCargo(cargo2);
        arrivingFlights.add(arrivingFlight);

        airport.setDepartingFlights(departingFlights);
        airport.setArrivingFlights(arrivingFlights);

        when(airportService.airportByIataAirportCode(any(String.class))).thenReturn(airport);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/airport-info")
                        .param("airportCode", airportCode)
                        .param("date", dateTime.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departingFlights").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.arrivingFlights").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.arrivingBaggagePieces").value(0)) // Add assertions for baggage pieces
                .andExpect(MockMvcResultMatchers.jsonPath("$.departingBaggagePieces").value(0)); // Add assertions for baggage pieces
    }
}
