package com.bidder.userservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bidder.userservice.dto.AuthRequest;
import com.bidder.userservice.dto.AuthResponse;
import com.bidder.userservice.dto.RegisterRequest;
import com.bidder.userservice.dto.UserAuthenticationInfo;
import com.bidder.userservice.service.AuthService;
import com.bidder.userservice.service.JwtService;
import com.bidder.userservice.service.UserService;

import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService service;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(AuthService service, JwtService jwtService, UserService userService) {
        this.service = service;
		this.jwtService = new JwtService();
		this.userService = new UserService();
    }
    
    //FOR TESTING
    @RequestMapping("/resource")
    public String getResource(@RequestHeader HttpHeaders headers) {
        // Log the headers
        headers.forEach((key, value) -> {
            System.out.println(String.format("Header '%s' = %s", key, value));
        });

        // Your controller logic here
        return "Resource accessed successfully";
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
        @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(
        @RequestBody AuthRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
    
    @PostMapping("/validateToken")
    public ResponseEntity<?> validateToken(@RequestBody String token) {
        try {
            String username = jwtService.extractUsername(token);
            UserDetails userDetails = userService.loadUserByUsername(username);
            if (jwtService.isTokenValid(token, userDetails)) {
                UserAuthenticationInfo userInfo = new UserAuthenticationInfo();
                userInfo.setUsername(userDetails.getUsername());
                userInfo.setRoles(userDetails.getAuthorities()
                                             .stream()
                                             .map(GrantedAuthority::getAuthority)
                                             .collect(Collectors.toList()));
                return ResponseEntity.ok(userInfo);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

}
