package com.smart4aviation.airport.controller;


import com.smart4aviation.airport.model.Airport;
import com.smart4aviation.airport.service.AirportService;
import com.smart4aviation.airport.util.AirportUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class AirportController {

    @Autowired
    AirportService airportService;

    @GetMapping("/airport-info")
    public ResponseEntity<Map<String, Object>> getAirportInfo(
            @RequestParam("airportCode") String airportCode,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
            Airport airport = airportService.airportByIataAirportCode(airportCode);
            return ResponseEntity.ok(AirportUtils.airportInfoCalculations(airport, dateTime));
    }
}
