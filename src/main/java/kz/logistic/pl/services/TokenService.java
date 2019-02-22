package kz.logistic.pl.services;

public interface TokenService {

    String getNewToken(String username);

    boolean validateToken(String token);

}
