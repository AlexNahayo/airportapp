package com.smart4aviation.airport.util;

import com.smart4aviation.airport.model.Baggage;
import com.smart4aviation.airport.model.Cargo;
import com.smart4aviation.airport.model.CargoItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightUtils {

    public static Map<String, Object> flightInfoCalculations( Cargo cargo){
        List<Baggage> baggageList =  cargo.getBaggage();
        int baggageWeight = baggageList.stream().mapToInt(Baggage::getWeight).sum();
        List<CargoItem> cargoItemList  = cargo.getCargoItems();
        int cargoWeight = cargoItemList.stream().mapToInt(CargoItem::getWeight).sum();
        int totalWeight = baggageWeight + cargoWeight;

        Map<String, Object> response = new HashMap<>();
        response.put("cargoWeight", cargoWeight);
        response.put("baggageWeight", baggageWeight);
        response.put("totalWeight", totalWeight);

        return response;
    }
}
