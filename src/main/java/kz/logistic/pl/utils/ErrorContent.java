package kz.logistic.pl.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ErrorContent {
    String message;
    String error;
    String exception;
    HttpStatus status;
}
