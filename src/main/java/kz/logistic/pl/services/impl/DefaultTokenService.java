package kz.logistic.pl.services.impl;

import io.jsonwebtoken.JwtBuilder;
import kz.logistic.pl.services.TokenService;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class DefaultTokenService implements TokenService {

    @Value("${jwt_hs256_secret_key}")
    String jwtSecretKey;

    @Override
    public String getNewToken(String username) {
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Key secretKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        String jws = Jwts.builder().setSubject(username).signWith(secretKey).compact();
        return jws;
    }

    @Override
    public boolean validateToken(String token) {
        return false;
    }
}
