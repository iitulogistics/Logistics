package kz.logistic.pl.services;


public interface AuthenticationService {

    String generateToken(String username);

    boolean validateToken(String token);

    boolean isCorrect(String username, String password);

}
