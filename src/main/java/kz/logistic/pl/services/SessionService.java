package kz.logistic.pl.services;

import kz.logistic.pl.models.entities.LoginEntity;

public interface SessionService {

    public Long getLoginEntityIdByUsernameAndPassword(String username, String password);

    public LoginEntity getLoginEntityByLoginId(Long loginId);

}