package com.travelbnb.travelbnb.payload;

import com.travelbnb.travelbnb.entity.Country;
import com.travelbnb.travelbnb.entity.Location;
import jakarta.persistence.*;

public class PropertyDto {


    private Long id;


    private String name;


    private Integer noGuests;


    private Integer no_Bedrooms;


    private Integer no_Bathrooms;


    private Integer price;


    private Country country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNoGuests() {
        return noGuests;
    }

    public void setNoGuests(Integer noGuests) {
        this.noGuests = noGuests;
    }

    public Integer getNo_Bedrooms() {
        return no_Bedrooms;
    }

    public void setNo_Bedrooms(Integer no_Bedrooms) {
        this.no_Bedrooms = no_Bedrooms;
    }

    public Integer getNo_Bathrooms() {
        return no_Bathrooms;
    }

    public void setNo_Bathrooms(Integer no_Bathrooms) {
        this.no_Bathrooms = no_Bathrooms;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    private Location location;

}
