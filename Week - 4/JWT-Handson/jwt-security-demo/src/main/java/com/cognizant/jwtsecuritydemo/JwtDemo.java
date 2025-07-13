package com.cognizant.jwtsecuritydemo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtDemo {
    public static void main(String[] args) {
        // HS256 requires a key of at least 32 bytes
        String secret = "secretkeysecretkeysecretkeysecretkey12";

        String jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject("1234567890")
                .claim("name", "John Doe")
                .claim("admin", true)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                .compact();

        System.out.println("Generated JWT: " + jwt);
    }
} 