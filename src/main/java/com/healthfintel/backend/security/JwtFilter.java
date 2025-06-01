package com.healthfintel.backend.security;

import com.healthfintel.backend.model.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import org.springframework.util.AntPathMatcher;

@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(AUTHORIZATION);
        String userId = null;
        String jwtToken = null;


        String path = request.getServletPath();

        if (pathMatcher.match("/auth/**", path)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }


        if (authHeader != null && authHeader.startsWith("Bearer ")){
            jwtToken = authHeader.substring(7);
            try{
                userId = jwtUtils.getUserIdFromToken(jwtToken);
            }catch (ExpiredJwtException ex) {
                // Token süresi dolmuş
                throw new AuthenticationCredentialsNotFoundException("Token expired", ex);
            } catch (JwtException | IllegalArgumentException ex) {
                // Geçersiz token formatı veya imza hatası
                throw new BadCredentialsException("Invalid token", ex);
            }
        }

        //Eğer token geçerliyse
        if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null){
            User user = customUserDetailsService.loadUserByUserId(Long.valueOf(userId));
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());

            if (jwtUtils.validateToken(jwtToken, user.getId())){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);


            }

        }


         filterChain.doFilter(request,response);

    }
}
