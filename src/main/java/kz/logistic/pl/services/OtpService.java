package kz.logistic.pl.services;

import java.io.IOException;

public interface OtpService {

    public boolean generateOtp(String mobilePhone) throws IOException;

    public boolean validateOtp(String mobilePhone, String otp);

}
