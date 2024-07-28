package com.travelbnb.travelbnb.service;

import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.entity.Favourite;
import com.travelbnb.travelbnb.entity.Property;
import com.travelbnb.travelbnb.payload.FavouriteDto;
import com.travelbnb.travelbnb.repository.FavouriteRepository;
import org.springframework.stereotype.Service;

@Service
public class FavouriteService {
private FavouriteRepository favouriteRepository;

    public FavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    Favourite dtoToEntity(FavouriteDto dto1, AppUser user,Property property){
        Favourite entity= new Favourite();
        entity.setStatus(dto1.getStatus());
        entity.setProperty(property);
        entity.setAppUser(user);
//        entity.setAppUser(dto1.getAppUser());  if we pass these 2 lines then we will get null
//        entity.setProperty(dto1.getProperty());
        return entity;
    }

    FavouriteDto entityToDto(Favourite en){
        FavouriteDto dto=new FavouriteDto();
        dto.setId(en.getId());
        dto.setStatus(en.getStatus());
        dto.setProperty(en.getProperty());
        dto.setAppUser(en.getAppUser());
        return dto;
    }

    public FavouriteDto addToFav(AppUser user, Property property, FavouriteDto dto) {

        Favourite favourite = dtoToEntity(dto, user, property);
        Favourite saved = favouriteRepository.save(favourite);
        FavouriteDto dto1 = entityToDto(saved);
        return dto1;
    }
}
