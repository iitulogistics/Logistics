package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.Roles;
import lombok.Builder;

@Builder
public class DefaultRoles implements Roles {
  private Long roleId;
  private String roleName;
  private String roleDescription;

  @Override
  public long getRoleId() {
    return roleId;
  }

  @Override
  public String getRoleName() {
    return roleName;
  }

  @Override
  public String getRoleDescription() {
    return roleDescription;
  }

}
