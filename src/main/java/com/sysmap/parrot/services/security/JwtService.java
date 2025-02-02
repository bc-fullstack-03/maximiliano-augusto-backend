package com.sysmap.parrot.services.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;

import java.util.Date;
import java.util.UUID;
import java.util.function.Function;
@Service
public class JwtService implements IJwtService{

    private final long EXPIRATION_TIME = 7200000;
    private final String KEY = "6251655468566D597133743677397A24432646294A404E635266556A586E5A72";

    public String generateToken(UUID userId){
        return Jwts
                .builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(genSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isValidToken(String token, String userId){

        var sub = getClaim(token, Claims::getSubject);
        var tExpiration = getClaim(token, Claims::getExpiration);

        if(!sub.equals(userId)) {
            return false;
        }

        if(tExpiration.before(new Date())) {
            return false;
        }

        return true;
    }

    private <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        var claims = Jwts
                        .parserBuilder()
                        .setSigningKey(genSignInKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
        return claimsResolver.apply(claims);
    }

    private Key genSignInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(KEY));
    }
}
