package kz.logistic.pl.services;


import kz.logistic.pl.models.pojos.Login;

public interface AuthenticationService {

    String generateToken(String username);

    String validateToken(String token);

    boolean isCorrect(String username, String password);

    Login loginObject(String username, String password);

    String getRoleByUsername(String username);

    String getRoleByToken(String token);

}
