package com.tankclean.TankClean.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

                //1
@Component
public class JwtUtil {

    private final  String  SECRET="abcdefghijklmnopqrstuvwxyz1234567890!@#$%&*?/|";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());//jwt


    public String generateToken(String email, String role){
        return Jwts.builder()//Start building token
                .setSubject(email) //Store data inside token
                .claim("role",role)
                .setIssuedAt(new Date())//Token created time
                .setExpiration(new Date(System.currentTimeMillis()+86400000))//Token valid for 24 hours
                .signWith(key)//Uses secret key to secure token
                .compact();//Converts into JWT string
    }


    public String extractEmail(String token){//sed to read data from token
        return Jwts.parserBuilder()//Start parsing token
                .setSigningKey(key)//Use same secret key to verify token
                .build()
                .parseClaimsJws(token)//Decode token
                .getBody().getSubject();//Get stored data → email
    }

                    public String extractRole(String token) {
                        return (String) Jwts.parserBuilder()
                                .setSigningKey(key)
                                .build()
                                .parseClaimsJws(token)
                                .getBody()
                                .get("role");
                    }
    public boolean validateToken(String token , String email){
        return extractEmail(token).equals(email);
    }
}
