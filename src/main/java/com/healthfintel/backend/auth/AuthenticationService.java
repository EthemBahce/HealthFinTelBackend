package com.healthfintel.backend.auth;


import com.healthfintel.backend.dto.LoginRequestDto;
import com.healthfintel.backend.dto.LoginResponseDto;
import com.healthfintel.backend.model.User;
import com.healthfintel.backend.security.CustomUserDetailsService;
import com.healthfintel.backend.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService userDetailsService;

    private final JwtUtils jwtUtils;



    public LoginResponseDto authenticate(LoginRequestDto request){


        try{

            var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        }catch (BadCredentialsException ex){
            throw new BadCredentialsException("Hatalı e-posta veya şifre.", ex);
        }

        User user = userDetailsService.loadUserByUserEmail(request.getEmail());

        var token = jwtUtils.generateToken(user.getId());

        //long expireTime = jwtUtils.getClaimFromToken(token, claims -> claims.getExpiration().getTime());


        return LoginResponseDto.builder()
                .token(token)
                .build();

    }

}
