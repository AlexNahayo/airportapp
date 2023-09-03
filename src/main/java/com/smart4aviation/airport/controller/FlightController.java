package com.smart4aviation.airport.controller;

import com.smart4aviation.airport.model.Cargo;
import com.smart4aviation.airport.service.FlightService;
import com.smart4aviation.airport.util.FlightUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping("/flight-info")
    public ResponseEntity<Map<String, Object>> getFlightInfo(
        @RequestParam("flightNumber") Integer flightNumber,
        @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {

        Cargo cargo = flightService.findCargoByFlightNumberAndDate(flightNumber, dateTime);

        return ResponseEntity.ok(FlightUtils.flightInfoCalculations(cargo));
    }
}
