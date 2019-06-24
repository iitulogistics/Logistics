package kz.logistic.pl;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class MobilePhone {


    @Size(min = 10, max = 11, message = "Номер телефона должен быть из 10 или 11 символов")
    @Pattern(regexp = "^[7-8][0-9]{1,10}$", message = "Номер телефона должен содержать только цифры")
    private String mobilePhone;
}
