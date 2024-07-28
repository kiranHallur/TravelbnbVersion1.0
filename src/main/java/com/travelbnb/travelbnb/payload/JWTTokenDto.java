package com.travelbnb.travelbnb.payload;

import lombok.Data;

@Data
public class JWTTokenDto {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;
}
