package com.smart4aviation.airport.repository;


import com.smart4aviation.airport.model.Cargo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CargoRepositoryTest {

    @Autowired
    CargoRepository cargoRepository;


    Cargo cargo;

    @BeforeEach
    public void setUp(){
        cargo = new Cargo();
    }

    @Test
    public void findByFlightId_add_new_cargo(){
        cargoRepository.save(cargo);
        assertNotNull(cargo.getCargoId());
    }
    @Test
    public void save_add_new_cargo(){
        cargoRepository.save(cargo);
        assertNotNull(cargo.getCargoId());
    }

    @Test
    public void delete_add_new_cargo(){
        cargoRepository.save(cargo);
        assertNotNull(cargo.getCargoId());
    }

}
