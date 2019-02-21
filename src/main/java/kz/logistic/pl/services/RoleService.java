package kz.logistic.pl.services;

import java.util.List;
import kz.logistic.pl.models.pojos.Roles;
import kz.logistic.pl.models.pojos.json.RolesJson;


public interface RoleService {

  List<Roles> showAllRoles();

  void addRole(String name, String description);

  void addRoleJson(RolesJson roles);

  Roles showRole(Long rolesId);

  String updateRole(Long cityId, RolesJson cityJson);

  String deleteRole(Long rolesId);

}
