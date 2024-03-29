package kz.logistic.pl.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import kz.logistic.pl.utils.ReturnMessage;
import kz.logistic.pl.models.entities.RolesEntity;
import kz.logistic.pl.models.pojos.Roles;
import kz.logistic.pl.models.pojos.impl.DefaultRoles;
import kz.logistic.pl.models.pojos.json.RolesJson;
import kz.logistic.pl.repositories.RoleRepository;
import kz.logistic.pl.services.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class DefaultRoleService implements RoleService {
    private RoleRepository roleRepository;
    private ReturnMessage returnMessage;

    @Autowired(required = false)
    public void setReturnMessage(ReturnMessage returnMessage) {
        this.returnMessage = returnMessage;
    }
    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Roles> showAllRoles() {
        List<RolesEntity> rolesEntities = this.roleRepository.findAll();

        return rolesEntities.stream().map(rolesEntity -> DefaultRoles.builder()
            .roleId(rolesEntity.getRoleId())
            .roleName(rolesEntity.getRoleName())
            .roleDescription(rolesEntity.getDescription()).build()
        ).collect(Collectors.toList());
    }

    @Override
    public Roles showRole(Long rolesId) {
        RolesEntity rolesEntity = roleRepository.findById(rolesId).orElse(null);

        return DefaultRoles.builder()
            .roleId(rolesEntity.getRoleId())
            .roleName(rolesEntity.getRoleName())
            .roleDescription(rolesEntity.getDescription()).build();
    }

    @Override
    public String addRole(String name, String description) {
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setRoleName(name);
        rolesEntity.setDescription(description);

        this.roleRepository.save(rolesEntity);
        log.info("Added new Role " + name + " " + new Date());
        return java.text.MessageFormat.format( returnMessage.getRoleAddSuccess(), rolesEntity.getRoleName());
    }

    @Override
    public String addRoleJson(RolesJson roles) {
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setRoleName(roles.getRoleName());
        rolesEntity.setDescription(roles.getRoleDescription());
        this.roleRepository.save(rolesEntity);
        log.info("Added new Role с помощью JSON формата " + roles.getRoleName() + " " + new Date());
        return java.text.MessageFormat.format( returnMessage.getRoleAddSuccess(), rolesEntity.getRoleName());
    }

    @Override
    public String updateRole(Long rolesId, RolesJson rolesJson) {
        RolesEntity rolesEntity = roleRepository.findById(rolesId).orElse(null);
        if (Objects.nonNull(rolesEntity)) {
            if (rolesJson.getRoleName() != null)
                rolesEntity.setRoleName(rolesJson.getRoleName());
            if (rolesJson.getRoleDescription() != null)
                rolesEntity.setDescription(rolesJson.getRoleDescription());
            this.roleRepository.save(rolesEntity);
            log.info("Updated " + rolesJson.getRoleName() + " role " + new Date());
            return java.text.MessageFormat.format(returnMessage.getRoleUpdateSuccess(), rolesEntity.getRoleName());

        } else {

            return java.text.MessageFormat.format(returnMessage.getRoleUpdateError(), rolesEntity.getRoleName());

        }
    }

    @Override
    public String deleteRole(Long rolesId) {
        RolesEntity rolesEntity = this.roleRepository.findById(rolesId).orElse(null);
        if (Objects.nonNull(rolesEntity)) {
            log.info("Updated " + rolesEntity.getRoleName() + " role" + new Date());
            this.roleRepository.delete(rolesEntity);
            return java.text.MessageFormat.format(returnMessage.getRoleDeleteSuccess(), rolesEntity.getRoleName());

        } else {
            return java.text.MessageFormat.format(returnMessage.getRoleDeleteError(), rolesEntity.getRoleName());

        }
    }

}
