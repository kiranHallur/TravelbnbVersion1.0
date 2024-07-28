package com.travelbnb.travelbnb.repository;

import com.travelbnb.travelbnb.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    boolean existsByname(String name);
}