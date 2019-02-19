package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.json.RolesJson;
import kz.logistic.pl.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Роли"}, description = "API для ролей компаний продавцов")
@RestController
@RequestMapping("/seller/company/role")
public class RolesController {

  private RoleService roleService;

  @Qualifier("defaultRoleService")
  @Autowired(required = false)
  public void setRoleService(RoleService roleService) {
    this.roleService = roleService;
  }

  @ApiOperation(value = "Показывает все списки ролей компаний продавцов")
  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.roleService.showAllRoles());
  }

  @ApiOperation(value = "Добавляет новые роли для компаний продавцов")
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity<?> add(
    @RequestParam(required = false) String roleName,
    @RequestParam(required = false) String roleDescription) {
    this.roleService.addRole(roleName, roleDescription);
    return ResponseEntity.ok("Новая роль компаний продавцов добавлена");
  }

  @ApiOperation(value = "Добавляет новые роли для компаний продавцов с помощью JSON формата")
  @RequestMapping(value = "/addJson", method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> addJson(@RequestBody RolesJson roles) {
    this.roleService.addRoleJson(roles);
    return ResponseEntity.ok("Новая роль компаний продавцов добавлена с помощью JSON формата");
  }



}
