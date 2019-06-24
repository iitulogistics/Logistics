package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.Address;
import kz.logistic.pl.models.pojos.LocalizedMessage;
import kz.logistic.pl.models.pojos.Login;
import lombok.Builder;

@Builder
public class DefaultLogin implements Login {

    private Long loginId;
    private String username;
    private String password;
    private Long rolesId;
    private Long customerId;
    private Long sellerCompanyId;
    private Long shipperId;

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRolesId(Long rolesId) {
        this.rolesId = rolesId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setSellerCompanyId(Long sellerCompanyId) {
        this.sellerCompanyId = sellerCompanyId;
    }

    public void setShipperId(Long shipperId) {
        this.shipperId = shipperId;
    }

    @Override
    public Long getLoginId() {
        return loginId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Long getRoleId() {
        return rolesId;
    }

    @Override
    public Long getCustomerId() {
        return customerId;
    }

    @Override
    public Long getSellerCompanyId() {
        return sellerCompanyId;
    }

    @Override
    public Long getShipperId() {
        return shipperId;
    }
}
