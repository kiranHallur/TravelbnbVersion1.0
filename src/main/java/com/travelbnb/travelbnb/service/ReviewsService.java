package com.travelbnb.travelbnb.service;

import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.entity.Property;
import com.travelbnb.travelbnb.entity.Reviews;
import com.travelbnb.travelbnb.payload.ReviewsDto;
import com.travelbnb.travelbnb.repository.ReviewsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewsService {

   private ReviewsRepository reviewsRepository;

    public ReviewsService(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    Reviews dtoToEntity(ReviewsDto dto,AppUser user,Property property){
        Reviews entity=new Reviews();
        entity.setRatings(dto.getRatings());
        entity.setDescription(dto.getDescription());
        entity.setAppUser(user); //it has user details and set the authenticated user
        entity.setProperty(property); //has property details and set the property fetched from the database
        entity.setAppUser(dto.getAppUser());
//        entity.setProperty(dto.getProperty());
        return entity;
    }

    ReviewsDto EntityToDto(Reviews en){
        ReviewsDto dto=new ReviewsDto();
        dto.setId(en.getId());
        dto.setRatings(en.getRatings());
        dto.setDescription(en.getDescription());
        dto.setAppUser(en.getAppUser());
        dto.setProperty(en.getProperty());
        return dto;
    }


    public ReviewsDto addReviews(AppUser user, Property property, ReviewsDto reviewsDto) {
        Reviews reviews=dtoToEntity(reviewsDto,user,property);
        Reviews saved = reviewsRepository.save(reviews);
        ReviewsDto dto = EntityToDto(saved);
        return dto;
    }




    public List<ReviewsDto> findByAppUserReview(AppUser appUser) {
        List<Reviews> reviews = reviewsRepository.findByAppUserReviews(appUser);
        List<ReviewsDto> reviewsDtoList = new ArrayList<>();
//        return reviews.stream()
//                .map(this::EntityToDto)
//                .collect(Collectors.toList());
        for (Reviews review : reviews) {
            ReviewsDto dto = EntityToDto(review);
            reviewsDtoList.add(dto);
        }

        return reviewsDtoList;
    }





}
