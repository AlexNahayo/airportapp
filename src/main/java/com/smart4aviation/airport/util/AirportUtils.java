package com.smart4aviation.airport.util;

import com.smart4aviation.airport.model.Airport;
import com.smart4aviation.airport.model.Baggage;
import com.smart4aviation.airport.model.Flight;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirportUtils {

   public static Map<String, Object> airportInfoCalculations(Airport airport, LocalDateTime dateTime){
           List<Flight> departingFlights = airport.getDepartingFlights();
           List<Flight> filteredDepartingFlights = departingFlights
                   .stream()
                   .filter(f -> f.getDepartureDate().isEqual(dateTime))
                   .toList();

           int totalDepartingBaggagePieces = filteredDepartingFlights.stream()
                   .map(Flight::getCargo)
                   .flatMap(cargo -> cargo.getBaggage().stream())
                   .mapToInt(Baggage::getPieces)
                   .sum();

           List<Flight> arrivingFlights = airport.getArrivingFlights();
           List<Flight> filteredArrivingFlights = arrivingFlights
                   .stream()
                   .filter(f -> f.getArrivalDate().isEqual(dateTime))
                   .toList();

           int totalArrivingBaggagePieces = filteredArrivingFlights.stream()
                   .map(Flight::getCargo)
                   .flatMap(cargo -> cargo.getBaggage().stream())
                   .mapToInt(Baggage::getPieces)
                   .sum();

           Map<String, Object> response = new HashMap<>();
           response.put("departingFlights", filteredDepartingFlights.size());
           response.put("arrivingFlights", filteredArrivingFlights.size());
           response.put("arrivingBaggagePieces", totalArrivingBaggagePieces);
           response.put("departingBaggagePieces", totalDepartingBaggagePieces);
           return  response;
   }
}
