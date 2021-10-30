/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.security.jwt;

import com.se.webbanhang.security.userprincal.UserPrinciple;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 *
 * @author ASUS
 */
@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret = "hacuong";
    private int jwtExpiration = 86400;
    public String createToken(Authentication authentication)
    {
        UserPrinciple userPrinciple = (UserPrinciple)authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    //check valie khong
    public boolean validateToken(String token)
    {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch(SignatureException e)
        {
            logger.error("Invalid jwt Signature -> Message: {}", e);
        }catch(MalformedJwtException e)
        {
            logger.error("Invalid format tokem -> Message: {}", e);
        }catch(ExpiredJwtException e)
        {
            logger.error("Expired jwt tokem -> Message: {}", e);
        }catch(UnsupportedJwtException e)
        {
            logger.error("Unsupport jwt tokem -> Message: {}", e);
        }catch(IllegalArgumentException e)
        {
            logger.error("JWT claims string empty -> Message: {}", e);
        }
        return false;
    }
    //Tim username co ten la gi
    public String getUserNameFromToken(String token)
    {
        String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return userName;
    }
    //
}
