package ru.spring.semestrovka.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.semestrovka.dto.UserDto;

import ru.spring.semestrovka.security.jwt.details.UserDetailsImpl;

@RestController
public class ProfileControllerRest {
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profileRest")
    public ResponseEntity<UserDto> getSelf() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        System.out.println(userDetails);
        return ResponseEntity.ok(UserDto.builder()
                .id(userDetails.getUserId())
                .email(userDetails.getUsername())
                .build());
    }
}
