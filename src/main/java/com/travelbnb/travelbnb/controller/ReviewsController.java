package com.travelbnb.travelbnb.controller;

import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.entity.Property;
import com.travelbnb.travelbnb.entity.Reviews;
import com.travelbnb.travelbnb.payload.ReviewsDto;
import com.travelbnb.travelbnb.repository.PropertyRepository;
import com.travelbnb.travelbnb.repository.ReviewsRepository;
import com.travelbnb.travelbnb.service.ReviewsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewsController {

    private ReviewsService reviewsService;
    private PropertyRepository propertyRepository; //to get property details
    private final ReviewsRepository reviewsRepository;

    public ReviewsController(ReviewsService reviewsService, PropertyRepository propertyRepository,
                             ReviewsRepository reviewsRepository) {
        this.reviewsService = reviewsService;
        this.propertyRepository = propertyRepository;
        this.reviewsRepository = reviewsRepository;
    }

    @PostMapping("/addReview")
    public ResponseEntity<?> addReview(
            @AuthenticationPrincipal AppUser user, //the annotation will take automatically the user details comes to this obj from token
                                                  //AuthenticationPrincipal gives the details of the current user logged in
            @RequestParam Long propertyId,
            @Valid @RequestBody ReviewsDto reviewsDto
            ){
        Optional<Property> opProperty = propertyRepository.findById(propertyId);

        if (opProperty.isPresent()) {
            Property property = opProperty.get();

            if(reviewsRepository.findReviewByUser(user,property)!=null){
                return new ResponseEntity<>("review exists", HttpStatus.OK);
            }else {
                ReviewsDto dto = reviewsService.addReviews(user, property, reviewsDto);
                return new ResponseEntity<>(dto, HttpStatus.OK);
            }
        }
        else {
            return new ResponseEntity<>("Property not found with id: " + propertyId, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getReviewsByUser")
    public ResponseEntity<List<ReviewsDto>> getUserReviews(
            @AuthenticationPrincipal AppUser appUser

    ){
        List<ReviewsDto> review = reviewsService.findByAppUserReview(appUser);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

//    public ResponseEntity<List<Reviews>> getUserReviews(
//            @AuthenticationPrincipal AppUser user
//    ){
//        reviewsService.findByUser(user.getId());
//    }

}
