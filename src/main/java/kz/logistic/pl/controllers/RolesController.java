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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/seller/company/role")
public class RolesController {

  private RoleService roleService;

  @Qualifier("defaultRoleService")
  @Autowired(required = false)
  public void setRoleService(RoleService roleService) {
    this.roleService = roleService;
  }

  @ApiOperation(value = "Показывает все списки ролей компаний продавцов")
  @GetMapping(value = "/all")
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.roleService.showAllRoles());
  }

  @ApiOperation(value = "Показывает роль по ID")
  @GetMapping(value = "/{id}")
  public ResponseEntity<?> getId(@PathVariable(value = "id") Long rolesId){
    return ResponseEntity.ok(this.roleService.showRole(rolesId));
  }

  @ApiOperation(value = "Добавляет новые роли для компаний продавцов")
  @PostMapping(value = "/add")
  public ResponseEntity<?> add(
    @RequestParam(required = false) String roleName,
    @RequestParam(required = false) String roleDescription) {
    this.roleService.addRole(roleName, roleDescription);
    return ResponseEntity.ok("Новая роль компаний продавцов добавлена");
  }

  @ApiOperation(value = "Добавляет новые роли для компаний продавцов с помощью JSON формата")
  @PostMapping(value = "/addJson",
    consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> addJson(@RequestBody RolesJson roles) {
    this.roleService.addRoleJson(roles);
    return ResponseEntity.ok("Новая роль компаний продавцов добавлена с помощью JSON формата");
  }

  @ApiOperation(value = "Обновляет роль")
  @PatchMapping("{id}")
  public ResponseEntity<?> update(
    @PathVariable(value = "id") Long rolesId,
    @RequestBody RolesJson rolesJson
  ) {
    return ResponseEntity.ok(this.roleService.updateRole(rolesId, rolesJson));
  }

  @ApiOperation(value = "Удаляет роль")
  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long rolesId) {
    return ResponseEntity.ok(this.roleService.deleteRole(rolesId));
  }

}
