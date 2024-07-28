package com.travelbnb.travelbnb.repository;

import com.travelbnb.travelbnb.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    boolean existsByname(String name);
}