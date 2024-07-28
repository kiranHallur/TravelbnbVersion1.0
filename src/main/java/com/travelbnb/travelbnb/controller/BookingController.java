package com.travelbnb.travelbnb.controller;

import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.entity.Property;
import com.travelbnb.travelbnb.payload.BookingDto;
import com.travelbnb.travelbnb.repository.PropertyRepository;
import com.travelbnb.travelbnb.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    private BookingService bookingService;
    private PropertyRepository propertyRepository;

    public BookingController(BookingService bookingService, PropertyRepository propertyRepository) {
        this.bookingService = bookingService;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/addBooking")
    public ResponseEntity<?> addBooking(
            @AuthenticationPrincipal AppUser user,
            @RequestParam Long propertyId,
            @RequestBody BookingDto dto
            ){
        Optional<Property> opProperty = propertyRepository.findById(propertyId);

        if(opProperty.isPresent()){
            Property property = opProperty.get();
            BookingDto dto1 = bookingService.addToBooking(user, property, dto);
            return new ResponseEntity<>(dto1, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Property not found with id: " + propertyId, HttpStatus.NOT_FOUND);
        }
    }
}
