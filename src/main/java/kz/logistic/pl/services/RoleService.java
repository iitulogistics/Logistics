package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.Roles;
import kz.logistic.pl.models.pojos.json.RolesJson;

import java.util.List;

public interface RoleService {

    List<Roles> showAllRoles();

    void addRole(String name, String Description);

    void addRoleJson(RolesJson roles);
}
