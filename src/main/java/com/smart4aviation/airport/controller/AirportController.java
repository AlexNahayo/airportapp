package com.smart4aviation.airport.controller;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class AirportController {

    @GetMapping("/flight-info")
    public ResponseEntity<Map<String, Object>> getFlightInfo(
            @RequestParam("flightNumber") Integer flightNumber,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {

        // Implement logic to retrieve flight information and calculate cargo and baggage weights
        // Return the calculated values in a ResponseEntity

        // Example response map
        Map<String, Object> response = new HashMap<>();
        response.put("cargoWeight", 100);
        response.put("baggageWeight", 200);
        response.put("totalWeight", 300);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/airport-info")
    public ResponseEntity<Map<String, Object>> getAirportInfo(
            @RequestParam("airportCode") String airportCode,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {

        // Implement logic to retrieve airport information, count flights, and calculate baggage counts
        // Return the calculated values in a ResponseEntity

        // Example response map
        Map<String, Object> response = new HashMap<>();
        response.put("departingFlights", 5);
        response.put("arrivingFlights", 3);
        response.put("arrivingBaggagePieces", 150);
        response.put("departingBaggagePieces", 120);

        return ResponseEntity.ok(response);
    }
}
