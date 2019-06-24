package kz.logistic.pl.services.impl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import kz.logistic.pl.models.entities.LoginEntity;
import kz.logistic.pl.models.pojos.Login;
import kz.logistic.pl.models.pojos.impl.DefaultLogin;
import kz.logistic.pl.repositories.LoginRepository;
import kz.logistic.pl.services.AuthenticationService;


import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

    @Autowired
    private LoginRepository loginRepository;

    @Value("${jwt.key}")
    private String jwtKeyString;

    @Override
    public String getRoleByToken(String token) {
        try {
            Claims claims = Jwts
                .parser()
                .setSigningKey(new SecretKeySpec(
                    jwtKeyString.getBytes(),
                    SignatureAlgorithm.HS256.getJcaName()
                ))
                .parseClaimsJws(token).getBody();
            return (String) claims.get("role");
        } catch (Exception e) {
            return "error";
        }
    }

    @Override
    public String getRoleByUsername(String username) {
        LoginEntity loginEntity = this.loginRepository.findByUsername(username);
        if (loginEntity.getCustomerEntity() != null)
            return "customer";
        if (loginEntity.getSellerCompanyEntity() != null)
            return "sellerCompany";
        if (loginEntity.getShipperEntity() != null)
            return "shipper";
        return null;
    }

    @Override
    public boolean isCorrect(String username, String password) {
        LoginEntity loginEntity = this.loginRepository.findByUsernameAndPassword(username, password);
        return loginEntity != null;
    }

    @Override
    public Login loginObject(String username, String password) {
        LoginEntity loginEntity = this.loginRepository.findByUsernameAndPassword(username,password);
        Long sellerId;
        Long customerId;
        Long shipperId;
        Long rolesId;
        try {
            if (loginEntity.getSellerCompanyEntity().getSellerCompanyId() != null) {
                sellerId = loginEntity.getSellerCompanyEntity().getSellerCompanyId();
            }
        } catch (NullPointerException e) {
            sellerId = 0L;
        } finally {
            sellerId = 0L;
        }


        try {
            if (loginEntity.getCustomerEntity().getCustomerId() != null) {
                customerId = loginEntity.getCustomerEntity().getCustomerId();
            }
        } catch (NullPointerException e) {
            customerId = 0L;
        } finally {
            customerId = 0L;
        }


        try{
            if(loginEntity.getShipperEntity().getShipperId() != null){
                shipperId = loginEntity.getShipperEntity().getShipperId();
            }
        } catch (NullPointerException e){
            shipperId = 0L;
        } finally {
            shipperId = 0L;
        }

        if (loginEntity.getRolesId() == null){
            rolesId = 0L;
        } else{
            rolesId = loginEntity.getRolesId();
        }

        return DefaultLogin.builder()
            .loginId(loginEntity.getLoginId())
            .username(loginEntity.getUsername())
            .password(loginEntity.getPassword())
            .rolesId(rolesId)
            .customerId(customerId)
            .sellerCompanyId(sellerId)
            .shipperId(shipperId)
            .build();
    }

    @Override
    public String generateToken(String username) {
        String jwt = Jwts.builder()
            .setSubject(username)
            .claim("role", getRoleByUsername(username))
            .signWith(new SecretKeySpec(
                jwtKeyString.getBytes(),
                SignatureAlgorithm.HS256.getJcaName()
            )).compact();
        return jwt;
    }

    @Override
    public String validateToken(String token) {
        try {
            Jwts
                .parser()
                .setSigningKey(new SecretKeySpec(
                    jwtKeyString.getBytes(),
                    SignatureAlgorithm.HS256.getJcaName()
                ))
                .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            return "expired";
        } catch (MalformedJwtException e) {
            return "invalid token";
        } catch (SignatureException e) {
            return "invalid token";
        }
        return "OK";
    }
}
