
package com.project.HotelServer.Util;

import com.project.HotelServer.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtil {
    private final String SECRET_KEY="fb6722a503faa5d0d1479053cf020e1ba788eeba0b1331daabed4d56d37cfe8a";


    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails user){
        String username=extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }


    public <T> T extractClaim(String token, Function<Claims,T> resolver){
        Claims claims =extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public String generateToken(UserDetails user){
        String token= Jwts.builder().setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+24*60*60*1000))
                .signWith(getSigninKey())
                .compact();

        return token;
    }

    private SecretKey getSigninKey(){
        byte[] keyBytes= Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
