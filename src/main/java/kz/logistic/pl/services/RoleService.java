package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.Roles;

import java.util.List;

public interface RoleService {

    List<Roles> showAllRoles();

    void addRole(String name, String Description);
}
