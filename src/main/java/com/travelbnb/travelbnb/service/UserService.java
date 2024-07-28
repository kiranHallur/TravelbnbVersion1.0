package com.travelbnb.travelbnb.service;

import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.payload.AppUserDto;
import com.travelbnb.travelbnb.payload.LoginDto;
import com.travelbnb.travelbnb.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private AppUserRepository appUserRepository;

    private JWTService jwtService;

    public UserService(AppUserRepository appUserRepository, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }

    AppUser dtoToEntity(AppUserDto dto){
        AppUser entity = new AppUser();
        entity.setName(dto.getName());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
       // entity.setPassword(dto.getPassword());
        entity.setPassword(BCrypt.hashpw(dto.getPassword(),BCrypt.gensalt(10)));
        entity.setRole(dto.getRole());
        return entity;
    }

    AppUserDto EntityToDto(AppUser en){
        AppUserDto dto= new AppUserDto();
        dto.setId(en.getId());
        dto.setName(en.getName());
        dto.setUsername(en.getUsername());
        dto.setEmail(en.getEmail());
        dto.setRole(en.getRole());
        //dto.setPassword(en.getPassword());

        return dto;
    }

    public AppUserDto createUser(AppUserDto appUserDto) {
        AppUser entity = dtoToEntity(appUserDto);
        AppUser saved = appUserRepository.save(entity);
        AppUserDto dto = EntityToDto(saved);
        return dto;
    }

    public String verifyLogin(LoginDto loginDto) {
        Optional<AppUser> opUserName = appUserRepository.findByUsername(loginDto.getUsername());
        if(opUserName.isPresent()){
            AppUser appUser = opUserName.get();
           if (BCrypt.checkpw(loginDto.getPassword(), appUser.getPassword())){  //verify pass with decr
               String token=jwtService.genrateToken(appUser);  // generating token with secrete key
               return token;
           }
        }
        return null;
    }
}
