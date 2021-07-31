package com.longpc.myblogrestapi.bean;

import com.longpc.myblogrestapi.entity.AuthenEntity;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtCustomBean {
    @Value("${jwt.custom.key}")
    private String key;

    @Value("${jwt.custom.expired}")
    private long expired;

    public String generateJwtToken(AuthenEntity authenEntity) {
        return Jwts.builder()
                .setSubject(authenEntity.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+expired))
                .signWith(SignatureAlgorithm.ES256,key)
                .compact();
    }
    public String getSubjectFromJwtToken(String token){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateJwtToken(String token){
        try{
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        }catch (SignatureException e){

        }catch (MalformedJwtException e){

        }catch (ExpiredJwtException e){

        }catch (UnsupportedJwtException e){

        }catch (IllegalArgumentException e){

        }
        return false;
    }
}
