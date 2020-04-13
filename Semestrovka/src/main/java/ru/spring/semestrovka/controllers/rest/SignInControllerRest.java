package ru.spring.semestrovka.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.semestrovka.dto.SignInDto;
import ru.spring.semestrovka.dto.TokenDto;
import ru.spring.semestrovka.service.SignInService;

@RestController
public class SignInControllerRest {

    @Autowired
    private SignInService signInService;

    @PostMapping("/signInRest")
    public ResponseEntity<TokenDto> signIn(@RequestBody SignInDto signInDto) {
        return ResponseEntity.ok(signInService.signIn(signInDto));
    }
}
