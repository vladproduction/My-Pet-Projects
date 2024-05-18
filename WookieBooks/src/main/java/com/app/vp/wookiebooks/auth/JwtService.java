package com.app.vp.wookiebooks.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Service responsible for creating token mechanism;
 * -generateToken;
 * -createToken;
 * -and extracting claims;
 * */
@Service
public class JwtService {

    private final String SECRET_KEY = "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello";

    /**
     * Method for generate token;
     * Map<String, Object> map holds all generated tokens for users;
     * @param userName String (for that user);
     * @param password String;
     * @return generated token for user (using private method 'createToken');
     * */
    public String generateToken(String userName, String password){
        Map<String, Object> map = new HashMap<>();
        map.put("password", password);
        return createToken(userName, map); //create token for user with concerning claims (map)
    }

    /**
     * Method for creating token (setting meta inform for token);
     * @param userName String;
     * @param claims Map<String, Object> claims (specific values that can be needed);
     * @return String value of created token;
     * */
    private String createToken(String userName, Map<String, Object> claims){
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * Method to get all claims based on SECRET key and token by parsing;
     * @param token String token;
     * @return Claims: object representing as map (as result of parsing);
     * */
    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Method to get username from current token given as param;
     * @param token String;
     * @return username String;
     * */
    public String extractUserName(String token){
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    /**
     * Method to get password of user from current token given as param;
     * @param token String;
     * @return password as String value;
     * */
    public String extractPassword(String token){
        Claims claims = extractAllClaims(token);
        return claims.get("password") + "";
    }

    /**
     * Method to validate our token in case of matching userName and password extracted from token,
     * and data for user from db (userDetails);
     * @param token String;
     * @param userDetails UserDetails;
     * @return true as boolean value if both match;
     * */
    public boolean validateToken(String token, UserDetails userDetails){
        String userName = extractUserName(token);
        if(!userName.equals(userDetails.getUsername())){
            return false;
        }
        String password = extractPassword(token);
        System.out.println("password = " + password);//password from token (request in Postman)
        System.out.println("userDetails.password = " + userDetails.getPassword()); //password from db attach to user
        if(!password.equals(userDetails.getPassword())){
            return false;
        }
        return true;
    }



}
