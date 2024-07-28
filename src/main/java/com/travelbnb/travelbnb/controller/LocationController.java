package com.travelbnb.travelbnb.controller;

import com.travelbnb.travelbnb.payload.LocationDto;
import com.travelbnb.travelbnb.repository.LocationRepository;
import com.travelbnb.travelbnb.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    private LocationService locationService;
    private LocationRepository locationRepository;

    public LocationController(LocationService locationService, LocationRepository locationRepository) {
        this.locationService = locationService;
        this.locationRepository = locationRepository;
    }

    @PostMapping("/addLocation")
    public ResponseEntity<?> addLocation(
            @RequestBody LocationDto locationDto
    ){
        if (locationRepository.existsByname(locationDto.getName())){
            return new ResponseEntity<>("Location exists", HttpStatus.BAD_REQUEST);
        }

        LocationDto dto = locationService.addlocation(locationDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
