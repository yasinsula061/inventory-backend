package com.sula.inventory.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.sula.inventory.config.ApplicationPathsConfig;
import com.sula.inventory.dao.UsersDAO;
import com.sula.inventory.model.User;
import com.sula.inventory.security.jwt.payload.request.LoginRequest;
import com.sula.inventory.security.jwt.payload.request.SignupRequest;
import com.sula.inventory.security.jwt.payload.response.JwtResponse;
import com.sula.inventory.security.jwt.payload.response.MessageResponse;
import com.sula.inventory.security.jwt.JwtUtils;
import com.sula.inventory.service.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApplicationPathsConfig.ApiCtrl.CTRL)
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersDAO usersDAO;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (usersDAO.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (usersDAO.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        usersDAO.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}

