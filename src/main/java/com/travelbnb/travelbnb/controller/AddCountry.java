package com.travelbnb.travelbnb.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/AddNow")
public class AddCountry {

    @PostMapping("/newArea")
    public String addCountry(){
        return "done";
    }
}
