package com.smart4aviation.airport.service;

import com.smart4aviation.airport.model.Airport;
import com.smart4aviation.airport.repository.AirportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AirportServiceTest {

    @InjectMocks
    private AirportService airportService;

    @Mock
    private AirportRepository airportRepository;

    @Test
    public void givenIataAirportCode_whenAirportExists_thenReturnAirport() {
        String iataAirportCode = "ABC";
        Airport expectedAirport = new Airport();
        expectedAirport.setIataAirportCode(iataAirportCode);

        when(airportRepository.findAirportByIataAirportCode(iataAirportCode))
                .thenReturn(expectedAirport);

        Airport result = airportService.airportByIataAirportCode(iataAirportCode);
        assertEquals(iataAirportCode, result.getIataAirportCode());
    }

    @Test
    public void givenIataAirportCode_whenAirportNotExists_thenReturnNull() {
        String iataAirportCode = "XYZ";

        when(airportRepository.findAirportByIataAirportCode(iataAirportCode))
                .thenReturn(null);

        Airport result = airportService.airportByIataAirportCode(iataAirportCode);
        assertNull(result);
    }

}
