package com.smart4aviation.airport.controller;

import com.smart4aviation.airport.model.Baggage;
import com.smart4aviation.airport.model.Cargo;
import com.smart4aviation.airport.model.CargoItem;
import com.smart4aviation.airport.service.FlightService;
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

@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @Test
    public void getFlightInfo_return_valid_data() throws Exception {
        int flightNumber = 123;
        LocalDateTime dateTime = LocalDateTime.now();

        Cargo cargo = new Cargo();

        List<Baggage> baggageList = new ArrayList<>();
        Baggage baggage = new Baggage();
        baggage.setWeight(10);
        baggageList.add(baggage);

        List<CargoItem> cargoItemList = new ArrayList<>();
        CargoItem cargoItem = new CargoItem();
        cargoItem.setWeight(20);
        cargoItemList.add(cargoItem);

        cargo.setBaggage(baggageList);
        cargo.setCargoItems(cargoItemList);

        when(flightService.findCargoByFlightNumberAndDate(any(Integer.class), any(LocalDateTime.class))).thenReturn(cargo);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flight-info")
                        .param("flightNumber", Integer.toString(flightNumber))
                        .param("date", dateTime.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cargoWeight").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.baggageWeight").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalWeight").value(30));
    }
}

