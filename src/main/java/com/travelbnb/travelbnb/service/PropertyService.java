package com.travelbnb.travelbnb.service;

import com.travelbnb.travelbnb.entity.Property;
import com.travelbnb.travelbnb.payload.PropertyDto;
import com.travelbnb.travelbnb.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    Property dtoToEntity (PropertyDto dto){
        Property entity=new Property();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setNo_Bathrooms(dto.getNo_Bathrooms());
        entity.setNo_Bedrooms(dto.getNo_Bedrooms());
        entity.setNoGuests(dto.getNoGuests());
        entity.setCountry(dto.getCountry());
        entity.setLocation(dto.getLocation());
        return entity;
    }

    PropertyDto EntityToDto(Property en){
        PropertyDto dto=new PropertyDto();
        dto.setId(en.getId());
        dto.setName(en.getName());
        dto.setPrice(en.getPrice());
        dto.setNo_Bathrooms(en.getNo_Bathrooms());
        dto.setNo_Bedrooms(en.getNo_Bedrooms());
        dto.setNoGuests(en.getNoGuests());
        dto.setCountry(en.getCountry());
        dto.setLocation(en.getLocation());
        return dto;
    }


    public PropertyDto addProperty(PropertyDto propertyDto) {
        Property entity = dtoToEntity(propertyDto);
        Property saved = propertyRepository.save(entity);
        PropertyDto dto = EntityToDto(saved);
        return dto;
    }


    public List<Property> searchByName(String name) {
        List<Property> properties = propertyRepository.searchProperty(name);
        return properties;
    }
}
