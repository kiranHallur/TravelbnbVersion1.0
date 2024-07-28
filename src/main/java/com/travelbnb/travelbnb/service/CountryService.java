package com.travelbnb.travelbnb.service;

import com.travelbnb.travelbnb.entity.Country;
import com.travelbnb.travelbnb.payload.CountryDto;
import com.travelbnb.travelbnb.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    Country dtoToEntity(CountryDto dto){
        Country entity=new Country();
        entity.setName(dto.getName());
        return entity;
    }

    CountryDto EntityToDto(Country en){
        CountryDto dto=new CountryDto();
        dto.setId(en.getId());
        dto.setName(en.getName());
        return dto;
    }


    public CountryDto addCountry(CountryDto countryDto) {
        Country entity = dtoToEntity(countryDto);
        Country saved = countryRepository.save(entity);
        CountryDto dto = EntityToDto(saved);
        return dto;
    }
}
