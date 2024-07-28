package com.travelbnb.travelbnb.controller;

import com.travelbnb.travelbnb.entity.Property;
import com.travelbnb.travelbnb.payload.PropertyDto;
import com.travelbnb.travelbnb.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    @PostMapping("/addProperty")
    public ResponseEntity<PropertyDto> createProperty(
            @RequestBody PropertyDto propertyDto
    ){
        PropertyDto dto = propertyService.addProperty(propertyDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/search/properties")
   public ResponseEntity<List<Property>> searchProperty(
           @RequestParam String name
    ){
        List<Property> properties = propertyService.searchByName(name);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }
}
