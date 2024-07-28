package com.travelbnb.travelbnb.controller;

import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.entity.Property;
import com.travelbnb.travelbnb.payload.FavouriteDto;
import com.travelbnb.travelbnb.repository.FavouriteRepository;
import com.travelbnb.travelbnb.repository.PropertyRepository;
import com.travelbnb.travelbnb.service.FavouriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/favourite")
public class FavouriteController {

    private FavouriteService favouriteService;
    private PropertyRepository propertyRepository;
    private final FavouriteRepository favouriteRepository;

    public FavouriteController(FavouriteService favouriteService, PropertyRepository propertyRepository,
                               FavouriteRepository favouriteRepository) {
        this.favouriteService = favouriteService;
        this.propertyRepository = propertyRepository;
        this.favouriteRepository = favouriteRepository;
    }

    //here we are adding to favourite i.e status=true if we to dislike or status=false then go for update method or del method
    @PostMapping("/addFav")
    public ResponseEntity<?> addFavourite(
            @AuthenticationPrincipal AppUser user,
            @RequestParam Long propertyId,
            @RequestBody FavouriteDto dto
            ){
        Optional<Property> opProperty = propertyRepository.findById(propertyId);

        if (opProperty.isPresent()){
            Property property = opProperty.get();
            FavouriteDto dto1 = favouriteService.addToFav(user, property, dto);
            return new ResponseEntity<>(dto1, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Property not found with id: " + propertyId, HttpStatus.NOT_FOUND);
        }
    }
}
