package com.travelbnb.travelbnb.controller;

import com.travelbnb.travelbnb.payload.CountryDto;
import com.travelbnb.travelbnb.repository.CountryRepository;
import com.travelbnb.travelbnb.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    private CountryService countryService;
    private CountryRepository countryRepository;

    public CountryController(CountryService countryService, CountryRepository countryRepository) {
        this.countryService = countryService;
        this.countryRepository = countryRepository;
    }

    @PostMapping("/addCountry")
    public ResponseEntity<?> addCountry(
            @RequestBody CountryDto countryDto
    ){
        if (countryRepository.existsByname(countryDto.getName())){
            return new ResponseEntity<>("Country exists", HttpStatus.BAD_REQUEST);
        }

        CountryDto country = countryService.addCountry(countryDto);
        return new ResponseEntity<>(country, HttpStatus.CREATED);

    }
}
