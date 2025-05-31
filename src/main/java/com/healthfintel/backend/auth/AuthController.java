package com.healthfintel.backend.auth;


import com.healthfintel.backend.dto.LoginRequestDto;
import com.healthfintel.backend.dto.LoginResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {

    private final AuthenticationService authenticationService;


    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto request){

        LoginResponseDto loginResponseDto = authenticationService.authenticate(request);

        return ResponseEntity.ok(loginResponseDto);
    }

}
