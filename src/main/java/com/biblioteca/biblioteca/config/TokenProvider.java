package com.biblioteca.biblioteca.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class TokenProvider {

    @Value("${jwt.expiration}")
    private long expirationTime;

    @Value("${jwt.key}")
    private String key;

    //gerar token
    public String gerarToken(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return buildToken(user.getUsername());
    }

    private String buildToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime);
        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSigninKey())
                .compact();
    }

    private SecretKey getSigninKey() {
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    //validar token

    public boolean isTokenValid(String token) {
        try{
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //pegar informações do meio do token(user, etc) = Claims
    private Claims getClaims(String token) {




        return Jwts.parser()
                .verifyWith(getSigninKey()) // validar assinatura
                .build()
                .parseSignedClaims(token) //validar expiração
                .getPayload(); // pegar o claim
    }


    //extrair informações (subject) do token, no caso username
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

}
