package com.example.api_med.controller;

import com.example.api_med.dto.DataRequestLogin;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity logIn(@RequestBody @Valid DataRequestLogin dataRequestLogin)
    {

        var token = new UsernamePasswordAuthenticationToken(dataRequestLogin.login(),dataRequestLogin.password());

        var authentication = authenticationManager.authenticate(token);

        return ResponseEntity.ok().build();
    }

}
