package com.getir.readingisgood;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;

public class TestTokenUtils {

    public static String getJWTToken(){
        return Jwts.builder()
                .setClaims(new HashMap<String,Object>())
                .setSubject("getir")
                .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) //expire after 1 hour
                .signWith(SignatureAlgorithm.HS512,"getirSecret").compact();
    }

}
