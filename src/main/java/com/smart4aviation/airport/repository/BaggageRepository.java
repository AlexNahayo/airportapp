package com.smart4aviation.airport.repository;

import com.smart4aviation.airport.model.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaggageRepository extends JpaRepository<Baggage, Long> {
}
