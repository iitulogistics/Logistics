package kz.logistic.pl.services.impl;

import java.util.ArrayList;

import kz.logistic.pl.models.entities.LoginEntity;
import kz.logistic.pl.repositories.LoginRepository;
import kz.logistic.pl.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;


public class DefaultSessionService implements SessionService {

  private LoginRepository loginRepository;

  @Autowired
  public void setLoginRepository(LoginRepository loginRepository) {
    this.loginRepository = loginRepository;
  }

  @Override
  public LoginEntity getLoginEntityByLoginId(Long loginId) {
    return loginRepository.getOne(loginId);
  }

  @Override
  public Long getLoginEntityIdByUsernameAndPassword(String username, String password) {
    ArrayList<LoginEntity> list =
      this.loginRepository.findByUsernameAndPassword(username, password);
    if (list.size() == 0) {
      return null;
    }
    return list.get(0).getLoginId();
  }

}