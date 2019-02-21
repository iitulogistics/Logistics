package kz.logistic.pl.services.impl;

import io.jsonwebtoken.*;
import kz.logistic.pl.models.entities.LoginEntity;
import kz.logistic.pl.repositories.LoginRepository;
import kz.logistic.pl.services.AuthenticationService;

import java.io.UnsupportedEncodingException;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.List;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

    @Autowired
    private LoginRepository loginRepository;
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Override
    public boolean isCorrect(String username, String password) {
        LoginEntity loginEntity = this.loginRepository.findByUsernameAndPassword(username, password);
        return loginEntity != null;
    }

    @Override
    public String generateToken(String username) {
        String jwt = Jwts.builder()
                .setSubject(username)
                .signWith(key).compact();
        return jwt;
    }

    @Override
    public boolean validateToken(String token) {
        try{
            Jwts
                    .parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token);
        }
        catch (ExpiredJwtException e){
            return false;
        }
        catch (MalformedJwtException e){
            return false;
        }
        return true;
    }
}
