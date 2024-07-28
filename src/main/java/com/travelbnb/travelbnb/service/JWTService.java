package com.travelbnb.travelbnb.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.travelbnb.travelbnb.entity.AppUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    //need to read all the values from property file
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiryTime;

    //algo for encryp and decryp and we need to download the library  i.e java-jwt-4.2.1
    private Algorithm algorithm;

    @PostConstruct   //this method will automatically run when we start the project
    public void postConstruct() {
        algorithm = Algorithm.HMAC256(algorithmKey); //this will run before start of project
        //                  with algoKey the algo HMAC256 will produce encryp and decryp result
        //                      this algo only works with secrete key
    }

    private final String USER_NAME = "username";  //created constant for username

    //generating the token
    public String genrateToken(AppUser appUser) {
        return JWT.create()
                .withClaim(USER_NAME, appUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);
    } //to call this method we are modifying verifyLogin method in UserService and controller


    public String getUsername(String token) {

            DecodedJWT decodedJWT = JWT.require(algorithm).withIssuer(issuer).build().verify(token);  //explanation is in the book
            return decodedJWT.getClaim(USER_NAME).asString();  //the method is string and we shld return the string, so converted Claim to string

    }

}
