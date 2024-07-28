package com.travelbnb.travelbnb.config;

import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.payload.AppUserDto;
import com.travelbnb.travelbnb.repository.AppUserRepository;
import com.travelbnb.travelbnb.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private JWTService jwtService;

    private AppUserRepository appUserRepository;

    public JWTRequestFilter(JWTService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //we need to implement this doFilterInternal() from builtin class OncePerRequestFilter
        // bcz it has obj like request ("takes incoming req"),    response,   filterChain       extra expln in book

        String tokenHeader = request.getHeader("Authorization");
        //System.out.println(tokenHeader);
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")){
            String token = tokenHeader.substring(8, tokenHeader.length()-1);
            String username = jwtService.getUsername(token);    //passing token to JWTService class i.e service layer and calls getUsername() which is in service layer
            //System.out.println(username);

            Optional<AppUser> opUsername = appUserRepository.findByUsername(username); //from token it goes to repo to find username
            if (opUsername.isPresent()){
                AppUser appUser = opUsername.get();

                //created object of inbuilt class, bcz if username is present in repo we need to check his role to access the url

                UsernamePasswordAuthenticationToken authentication=
                        new UsernamePasswordAuthenticationToken(appUser,null,
                                Collections.singleton(new SimpleGrantedAuthority(appUser.getRole())));
                            //collection has multiple data but, we need only one data so, we used singleton

                authentication.setDetails(new WebAuthenticationDetails(request));
                //takes the authentication obj and setting url details and requesting springBoot

                SecurityContextHolder.getContext().setAuthentication(authentication);
                //using authentication obj springBoot allows us to access the url only if he is authenticated user
            }

        }

        filterChain.doFilter(request,response);   // this line tells that the method doFilterInternal() is called only when the url contains token


    }
}
