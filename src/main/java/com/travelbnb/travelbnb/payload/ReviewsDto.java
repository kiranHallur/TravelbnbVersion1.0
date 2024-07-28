package com.travelbnb.travelbnb.payload;

import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.entity.Property;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class ReviewsDto {

    private Long id;

    @Min(value = 1, message = "Ratings must be greater than or equal to 1")
    @Max(value = 5, message = "Ratings must be less than or equal to 5")
    private Integer ratings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRatings() {
        return ratings;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    private String description;

    private Property property;

    private AppUser appUser;

}
