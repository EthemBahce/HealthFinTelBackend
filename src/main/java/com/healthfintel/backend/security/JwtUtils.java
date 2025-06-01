package com.healthfintel.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {

    //gizli anahtar(application properties içerisinde tanımlı - 128)
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    //token geçerlilik süresi(application properties içerisinde mevcut)
    @Value("${application.security.jwt.expiration}")
    private Long jwtExpiration;


    //JWT içerisindeki payload bilgisini alır, yani subject alanını
    public String getUserIdFromToken(String token){
        System.out.println("Exract userid: " + token);
        return getClaimFromToken(token, Claims::getSubject);
    }


    //Claims değerini almamızı sağlar
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    //Token içerisindeki tüm Claimsleri parse için
    private Claims extractAllClaims(String token){

        try {

            return Jwts.parser()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (JwtException ex){
            throw new RuntimeException("JWT doğrulama hatası " + ex.getMessage());
        }

    }


    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Jwt geçerliliğini kontrol ediyoruz
    public boolean validateToken(String token, Long userId){
        final String userIdFromToken = getUserIdFromToken(token);
        return (userIdFromToken.equals(userId.toString()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        try {
            final Date expiration = extractAllClaims(token).getExpiration();
            return expiration.before(new Date());
        }catch (ExpiredJwtException ex){
            return true;
        }
    }

    public String generateToken(Long userId){

        return Jwts.builder()
                .setSubject(String.valueOf(userId)) //payload içerisindeki subject yani userId
                .setIssuedAt(new Date(System.currentTimeMillis()))//token oluşturulma zamanına eşit
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration)) // expire olacağı zamana eşit yani bitiş zamanı
                .signWith(getSignInKey())//imzalama algoritmasını çalıştıran metot
                .compact();

    }



}
