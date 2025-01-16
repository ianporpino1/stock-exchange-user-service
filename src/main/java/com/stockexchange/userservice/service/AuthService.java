package com.stockexchange.userservice.service;

import com.stockexchange.userservice.controller.dto.LoginRequest;
import com.stockexchange.userservice.controller.dto.LoginResponse;
import com.stockexchange.userservice.model.User;
import com.stockexchange.userservice.security.JwtService;
import com.stockexchange.userservice.security.UserAuthenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    private final JwtService jwtService;
    
    private final AuthenticationManager authenticationManager;

    public AuthService(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    public Long extractUserId(Jwt principal) {
        return jwtService.extractUserId(principal);
    }


    public LoginResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        UserAuthenticated UserAuthenticated  = (com.stockexchange.userservice.security.UserAuthenticated) authentication.getPrincipal();
        String token = jwtService.generateToken(authentication);
//        User user = UserAuthenticated.getUser();
        
        return new LoginResponse(token);
    }







    
   

}
