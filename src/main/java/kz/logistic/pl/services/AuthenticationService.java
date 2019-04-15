package kz.logistic.pl.services;


public interface AuthenticationService {

    String generateToken(String username);

    String validateToken(String token);

    boolean isCorrect(String username, String password);

    String getRoleByUsername(String username);

    String getRoleByToken(String token);

}
