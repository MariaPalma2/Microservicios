package com.maria.autenticacion.controller;

import com.maria.autenticacion.dto.AuthRequestDto;
import com.maria.autenticacion.dto.AuthResponseDto;
import com.maria.autenticacion.util.Error;
import com.maria.autenticacion.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthRequestDto request) {
        if (JwtUtil.USER.equals(request.username()) && JwtUtil.PASSWORD.equals(request.password())) {
            String token = this.jwtUtil.generateToken(request.username());
            return ResponseEntity.ok(new AuthResponseDto(token));
        }
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new Error("Credenciales incorrectas"));
    }
}