package com.travelbnb.travelbnb.service;

import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.entity.Booking;
import com.travelbnb.travelbnb.entity.Property;
import com.travelbnb.travelbnb.payload.BookingDto;
import com.travelbnb.travelbnb.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    Booking dtoToEntity(BookingDto dto, AppUser user, Property property){
        Booking entity= new Booking();
        entity.setName(dto.getName());
        entity.setMail(dto.getMail());
        entity.setPhone(dto.getPhone());
        entity.setDays(dto.getDays());
        entity.setNightlyPrice(dto.getNightlyPrice());
        entity.setAppUser(user);
        entity.setProperty(property);
//        int price = property.getPrice();
//        int totalPrice = price * dto.getDays();
//        entity.setNightlyPrice(totalPrice);
        return entity;
    }

    BookingDto EntityToDto(Booking en){
        BookingDto dto=new BookingDto();
        dto.setId(en.getId());
        dto.setName(en.getName());
        dto.setMail(en.getMail());
        dto.setPhone(en.getPhone());
        dto.setDays(en.getDays());
        dto.setNightlyPrice(en.getNightlyPrice());
        dto.setProperty(en.getProperty());
        dto.setAppUser(en.getAppUser());
        return dto;
    }


    public BookingDto addToBooking(AppUser user, Property property, BookingDto dto) {
        Booking booking = dtoToEntity(dto, user, property);
        int price = property.getPrice();
        int totalPrice = price * booking.getDays();
        booking.setNightlyPrice((float) totalPrice);
        Booking save = bookingRepository.save(booking);
        BookingDto dto1 = EntityToDto(save);
        return dto1;
    }
}
