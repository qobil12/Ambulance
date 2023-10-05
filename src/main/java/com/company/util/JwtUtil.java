package com.company.util;

import com.company.dto.JwtDTO;
import com.company.enums.Role;
import com.company.exceptions.BadRequestException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {
    private static final String secretKey = "ZTx7i3mVf9c2rGkE1sNpD5qAzLrXwGyB123avaQdzKJDA921LKCi";

    public static String encode(UUID id, Role role) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date()); // 18:58:00
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000))); // 19:58:00
        jwtBuilder.setIssuer("ZTx7i3mVf9c2rGkE1sNpD5qAzLrXwGyB");
        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey);
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("role", role.name());

        jwtBuilder.setClaims(claims);
        return jwtBuilder.compact();
    }

    public static UUID decode(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return UUID.fromString(claims.get("id").toString());
    }

    public static UUID decode(String token, Role requiredRole) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        UUID id = (UUID) claims.get("id");
        String role = (String) claims.get("role");

        if (!requiredRole.equals(Role.valueOf(role))) {
            throw new BadRequestException("Not Access");
        }
        return id;
    }

    public static JwtDTO decodeJwtDTO(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        UUID id = (UUID) claims.get("id");
        String role = (String) claims.get("role");

        return new JwtDTO(id, Role.valueOf(role));
    }

    public static String encode(UUID id) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date()); // 18:58:00
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 60 * 1000))); // 19:58:00
        jwtBuilder.setIssuer("Ambulance project");
        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey);
        jwtBuilder.claim("id", id);

        return jwtBuilder.compact();
    }
}
