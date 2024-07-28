package com.travelbnb.travelbnb.service;

import com.travelbnb.travelbnb.entity.Location;
import com.travelbnb.travelbnb.payload.LocationDto;
import com.travelbnb.travelbnb.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

   private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    Location dtoToEntity(LocationDto dto){
        Location entity=new Location();
        entity.setName(dto.getName());
        return entity;
    }

    LocationDto EntityToDto(Location en){
        LocationDto dto=new LocationDto();
        dto.setId(en.getId());
        dto.setName(en.getName());
        return dto;
    }

    public LocationDto addlocation(LocationDto locationDto) {
        Location entity = dtoToEntity(locationDto);
        Location save = locationRepository.save(entity);
        LocationDto dto = EntityToDto(save);
        return dto;
    }
}
