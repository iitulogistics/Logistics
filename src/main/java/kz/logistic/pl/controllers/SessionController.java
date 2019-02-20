package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import kz.logistic.pl.models.entities.LoginEntity;
import kz.logistic.pl.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@Api(tags = {"Сессии"}, description = "API для управдения сессиями")
@RestController
@RequestMapping(value = "/session")
public class SessionController {

  private SessionService sessionService;

  @Autowired(required = false)
  @Qualifier("defaultSessionService")
  public void setSessionService(SessionService sessionService) {
    this.sessionService = sessionService;
  }

  @ApiOperation("Возвращает логин текущего пользователя")
  @RequestMapping(value = "/get_login", method = RequestMethod.GET)
  public ResponseEntity<?> getUsername(HttpServletRequest request) {
    HttpSession session = request.getSession();
    Long loginEntityId = (Long) session.getAttribute("loginEntityId");
    LoginEntity loginEntity = this.sessionService.getLoginEntityByLoginId(loginEntityId);
    return ResponseEntity.ok(loginEntity.getUsername());
  }

  @ApiOperation("Производит вход в систему")
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ResponseEntity<?> login(
    @RequestParam String username,
    @RequestParam String password,
    HttpServletRequest request) {
    HttpSession session = request.getSession();
    Long loginEntityId = sessionService.getLoginEntityIdByUsernameAndPassword(username, password);
    if (loginEntityId == null) {
      return ResponseEntity.ok("Неверный логин или пароль");
    } else {
      session.setAttribute("loginEntityId", loginEntityId);
      return ResponseEntity.ok(session.getId());
    }
  }

  @ApiOperation("Выходит из системы")
  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  public ResponseEntity<?> logout(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.invalidate();
    return ResponseEntity.ok("logged out");
  }

}