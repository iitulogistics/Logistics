package kz.logistic.pl.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import kz.logistic.pl.utils.ReturnMessage;
import kz.logistic.pl.models.entities.LoginEntity;
import kz.logistic.pl.models.entities.SellerCompanyEntity;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.pojos.SellerCompany;
import kz.logistic.pl.models.pojos.impl.DefaultSellerCompany;
import kz.logistic.pl.models.pojos.json.SellerCompanyJson;
import kz.logistic.pl.repositories.LoginRepository;
import kz.logistic.pl.repositories.SellerCompanyRepository;
import kz.logistic.pl.services.SellerCompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class DefaultSellerCompanyService implements SellerCompanyService {

    private SellerCompanyRepository sellerCompanyRepository;
    private LocalizedMessageBuilderFactory localizedMessageBuilderFactory;
    private LoginRepository loginRepository;
    private ReturnMessage returnMessage;

    @Autowired(required = false)
    public void setReturnMessage(ReturnMessage returnMessage) {
        this.returnMessage = returnMessage;
    }
    @Autowired(required = false)
    public void setSellerCompanyRepository(SellerCompanyRepository sellerCompanyRepository) {
        this.sellerCompanyRepository = sellerCompanyRepository;
    }

    @Autowired(required = false)
    public void setLocalizedMessageBuilderFactory(
        LocalizedMessageBuilderFactory localizedMessageBuilderFactory) {
        this.localizedMessageBuilderFactory = localizedMessageBuilderFactory;
    }

    @Autowired(required = false)
    public void setLoginRepository(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public boolean exists(String username) {
        LoginEntity loginEntity = this.loginRepository.findByUsername(username);
        return loginEntity != null;
    }

    @Override
    public List<SellerCompany> showAllSellerCompanies() {
        List<SellerCompanyEntity> entities = this.sellerCompanyRepository.findAll();

        return entities.stream().map(sellerCompanyEntity -> DefaultSellerCompany.builder()
            .sellerCompanyId(sellerCompanyEntity.getSellerCompanyId())
            .loginId(sellerCompanyEntity.getLoginEntity().getLoginId())
            .username(sellerCompanyEntity.getLoginEntity().getUsername())
            .password(sellerCompanyEntity.getLoginEntity().getPassword())
            .sellerCompanyName(
                localizedMessageBuilderFactory.builder()
                    .kk(sellerCompanyEntity.getCompanyNameKk())
                    .ru(sellerCompanyEntity.getCompanyNameRu())
                    .en(sellerCompanyEntity.getCompanyNameEn()).build())
            .sellerCompanyPhone(sellerCompanyEntity.getPhone())
            .sellerCompanyMobilePhone(sellerCompanyEntity.getMobilePhone())
            .sellerCompanyBin(sellerCompanyEntity.getBin())
            .sellerCompanyEmail(sellerCompanyEntity.getEmail())
            .sellerCategoryId(sellerCompanyEntity.getSellerCategoryId())
            .build()).collect(Collectors.toList());

    }

    @Override
    public DefaultSellerCompany showSellerCompany(Long sellerCompanyId) {
        SellerCompanyEntity sellerCompanyEntity = this.sellerCompanyRepository.findById(sellerCompanyId).orElse(null);
        return DefaultSellerCompany.builder()
            .sellerCompanyId(sellerCompanyEntity.getSellerCompanyId())
            .sellerCategoryId(sellerCompanyEntity.getSellerCategoryId())
            .loginId(sellerCompanyEntity.getLoginEntity().getLoginId())
            .username(sellerCompanyEntity.getLoginEntity().getUsername())
            .password(sellerCompanyEntity.getLoginEntity().getPassword())
            .sellerCompanyName(localizedMessageBuilderFactory.builder()
                .en(sellerCompanyEntity.getCompanyNameEn())
                .ru(sellerCompanyEntity.getCompanyNameRu())
                .kk(sellerCompanyEntity.getCompanyNameKk()).build())
            .sellerCompanyEmail(sellerCompanyEntity.getEmail())
            .sellerCompanyBin(sellerCompanyEntity.getBin())
            .sellerCompanyMobilePhone(sellerCompanyEntity.getMobilePhone())
            .sellerCompanyPhone(sellerCompanyEntity.getPhone()).build();
    }

    @Override
    public String addSellerCompany(String sellerCompanyNameKk, String sellerCompanyNameRu,
                                   String sellerCompanyNameEn, String sellerCompanyPhone,
                                   String sellerCompanyMobilePhone, String sellerCompanyBin,
                                   String sellerCompanyEmail, String username, String password,
                                   Long sellerCompanyCategoryId) {

        if (exists(username))
            return returnMessage.getSellercompanyAddError();
        SellerCompanyEntity sellerCompanyEntity = new SellerCompanyEntity();
        sellerCompanyEntity.setCompanyNameKk(sellerCompanyNameKk);
        sellerCompanyEntity.setCompanyNameRu(sellerCompanyNameRu);
        sellerCompanyEntity.setCompanyNameEn(sellerCompanyNameEn);
        sellerCompanyEntity.setPhone(sellerCompanyPhone);
        sellerCompanyEntity.setMobilePhone(sellerCompanyMobilePhone);
        sellerCompanyEntity.setBin(sellerCompanyBin);
        sellerCompanyEntity.setEmail(sellerCompanyEmail);
        sellerCompanyEntity.setSellerCategoryId(sellerCompanyCategoryId);

        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(username);
        loginEntity.setPassword(password);

        this.sellerCompanyRepository.save(sellerCompanyEntity);
        loginEntity.setSellerCompanyEntity(sellerCompanyEntity);
        this.loginRepository.save(loginEntity);
        log.info("Added new seller company: " + sellerCompanyNameRu + " " + new Date());
        return returnMessage.getSellercompanyAddSuccess();

    }

    @Override
    public String addSellerCompanyJson(SellerCompanyJson sellerCompanyJson) {
        if (exists(sellerCompanyJson.getUsername()))
            return returnMessage.getSellercompanyAddError();
        SellerCompanyEntity sellerCompanyEntity = new SellerCompanyEntity();
        sellerCompanyEntity.setCompanyNameKk(sellerCompanyJson.getSellerCompanyNameKk());
        sellerCompanyEntity.setCompanyNameRu(sellerCompanyJson.getSellerCompanyNameRu());
        sellerCompanyEntity.setCompanyNameEn(sellerCompanyJson.getSellerCompanyNameEn());
        sellerCompanyEntity.setPhone(sellerCompanyJson.getSellerCompanyPhone());
        sellerCompanyEntity.setMobilePhone(sellerCompanyJson.getSellerCompanyMobilePhone());
        sellerCompanyEntity.setBin(sellerCompanyJson.getSellerCompanyBin());
        sellerCompanyEntity.setEmail(sellerCompanyJson.getSellerCompanyEmail());

        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(sellerCompanyJson.getUsername());
        loginEntity.setPassword(sellerCompanyJson.getPassword());
        loginEntity.setSellerCompanyEntity(sellerCompanyEntity);

        sellerCompanyEntity.setLoginEntity(loginEntity);

        this.sellerCompanyRepository.save(sellerCompanyEntity);
        this.loginRepository.save(loginEntity);

        log.info("Added new seller company: "
            + sellerCompanyJson.getSellerCompanyNameRu() + " via Json" + new Date());
        return java.text.MessageFormat.format(returnMessage.getSellercompanyAddSuccess(), sellerCompanyEntity.getCompanyNameEn());
    }

    @Override
    public String updateSellerCompany(Long sellerCompanyId, SellerCompanyJson sellerCompanyJson) {
        SellerCompanyEntity sellerCompanyEntity = this.sellerCompanyRepository.findById(sellerCompanyId).orElse(null);
        LoginEntity loginEntity = this.loginRepository.findById(sellerCompanyEntity.getLoginEntity().getLoginId()).orElse(null);
        if (Objects.nonNull(sellerCompanyEntity)) {
            if (sellerCompanyJson.getSellerCompanyNameRu() != null) {
                sellerCompanyEntity.setCompanyNameRu(sellerCompanyJson.getSellerCompanyNameRu());
            }
            if (sellerCompanyJson.getSellerCompanyNameEn() != null) {
                sellerCompanyEntity.setCompanyNameEn(sellerCompanyJson.getSellerCompanyNameEn());
            }
            if (sellerCompanyJson.getSellerCompanyNameKk() != null) {
                sellerCompanyEntity.setCompanyNameKk(sellerCompanyJson.getSellerCompanyNameKk());
            }
            if (sellerCompanyJson.getSellerCompanyBin() != null) {
                sellerCompanyEntity.setBin(sellerCompanyJson.getSellerCompanyBin());
            }
            if (sellerCompanyJson.getSellerCompanyEmail() != null) {
                sellerCompanyEntity.setEmail(sellerCompanyJson.getSellerCompanyEmail());
            }
            if (sellerCompanyJson.getSellerCompanyMobilePhone() != null) {
                sellerCompanyEntity.setMobilePhone(sellerCompanyJson.getSellerCompanyMobilePhone());
            }
            if (sellerCompanyJson.getSellerCompanyPhone() != null) {
                sellerCompanyEntity.setPhone(sellerCompanyJson.getSellerCompanyPhone());
            }
            if (sellerCompanyJson.getPassword() != null) {
                loginEntity.setPassword(sellerCompanyEntity.getLoginEntity().getPassword());
            }

            this.loginRepository.save(loginEntity);
            this.sellerCompanyRepository.save(sellerCompanyEntity);
            log.info("Updated " + sellerCompanyId + " sellerCompany " + new Date());
            return java.text.MessageFormat.format(returnMessage.getSellercompanyUpdateSuccess(), sellerCompanyEntity.getCompanyNameEn());

        } else {
            return java.text.MessageFormat.format(returnMessage.getSellercompanyUpdateError(), sellerCompanyEntity.getCompanyNameEn());

        }
    }

    @Override
    public String deleteSellerCompany(Long sellerCompanyId) {
        SellerCompanyEntity sellerCompanyEntity = this.sellerCompanyRepository.findById(sellerCompanyId).orElse(null);
        LoginEntity loginEntity = this.loginRepository.findById(sellerCompanyEntity.getLoginEntity().getLoginId()).orElse(null);
        this.loginRepository.deleteById(loginEntity.getLoginId());
        this.sellerCompanyRepository.deleteById(sellerCompanyEntity.getSellerCompanyId());
        return java.text.MessageFormat.format(returnMessage.getSellercompanyDeleteSuccess(), sellerCompanyEntity.getCompanyNameEn());

    }
}
