package com.travelbnb.travelbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;


@Configuration // makes normal class to spring-boot class
public class SecurityConfig {

    private JWTRequestFilter jwtRequestFilter;

    public SecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf().disable().cors().disable();  // take care of csrf and cors attacks.
        http.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);  //before sending any req, run this method (doFilterInternal() from jwtreqfilter class)
        //http.authorizeHttpRequests().anyRequest().permitAll();  // the objectParam http will make all the url keep open from security
        http.authorizeHttpRequests()
                .requestMatchers("/api/v1/user/login", "/api/v1/user/createUser","/api/v1/country/addCountry",
                "/api/v1/location/addLocation", "/api/v1/property/addProperty",
                        "/api/v1/property/search/properties", "/api/v1/reviews/addReview?propertyId").permitAll()    //the above 2 urls are accessed by everyone

                .requestMatchers("/api/v1/AddNow/newArea").hasRole("ADMIN")  //it says only admin can access above url

                //this url is accessed to authorised users

                .anyRequest().authenticated();


        return http.build(); //the build() will form obj and obj is given to spring-boot
    }



}
