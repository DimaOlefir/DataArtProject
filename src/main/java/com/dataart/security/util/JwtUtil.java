package com.dataart.security.util;

import com.dataart.entity.User;
import com.dataart.entity.enums.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Роман on 17.11.2017.
 */
@Component
public class JwtUtil {

    @Value("secret")
    private String secret;

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with login, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public User parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            User user = new User();
            user.setLogin(body.getSubject());
            //user.setUsername(body.getSubject());
            user.setId(Long.parseLong((String) body.get("userId")));
            //user.setRole((String) body.get("role"));
            user.setRole(Role.valueOf((String)body.get("role")));
            return user;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    /**
     * Generates a JWT token containing login as subject, and userId and
     * role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param user the user for which the token will be generated
     * @return the JWT token
     */
    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getLogin());
        claims.put("userId", user.getId() + "");
        claims.put("role", user.getRole() + "");

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

}
