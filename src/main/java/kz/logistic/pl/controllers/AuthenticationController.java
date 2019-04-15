package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Аутентификация"}, description = "API для создания и валидации токенов")
@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired(required = false)
    @Qualifier("defaultAuthenticationService")
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "Производит вход в систему. Возвращает токен если логин и пароль верны, " +
        "либо сообщение об ошибке в противном случае")
    public String login(
        @RequestParam String username,
        @RequestParam String password
    ) {
        if (!this.authenticationService.isCorrect(username, password))
            return "Incorrect username or password";
        return this.authenticationService.generateToken(username);
    }

    @GetMapping(value = "/validate")
    @ApiOperation(value = "Проверяет токен на достоверность")
    public String validate(String token) {
        return this.authenticationService.validateToken(token);
    }

    @GetMapping(value = "/test")
    public String authenticationTest(@RequestParam(value = "jwt_token") String jwtToken){
        //return this.authenticationService.getRoleByToken(jwtToken);
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
    }

}
