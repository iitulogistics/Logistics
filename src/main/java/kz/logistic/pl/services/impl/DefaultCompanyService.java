package kz.logistic.pl.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.pojos.Company;
import kz.logistic.pl.models.pojos.impl.DefaultCompany;
import kz.logistic.pl.repositories.LoginRepository;
import kz.logistic.pl.services.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class DefaultCompanyService implements CompanyService {
  private LoginRepository companyRepository;
  private LocalizedMessageBuilderFactory localizedMessageBuilderFactory;

  @Autowired(required = false)
  public void setCompanyRepository(LoginRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  @Autowired(required = false)
  public void setLocalizedMessageBuilderFactory(
          LocalizedMessageBuilderFactory localizedMessageBuilderFactory) {
    this.localizedMessageBuilderFactory = localizedMessageBuilderFactory;
  }

  @Override
  public List<Company> companies() {
    List<CompanyEntity> entities = this.companyRepository.findAll();
    return entities.stream().map(companyEntity -> DefaultCompany
            .builder()
            .id(companyEntity.getId())
            .companyName(
                    localizedMessageBuilderFactory.builder()
                            .kk(companyEntity.getNameKk())
                            .ru(companyEntity.getNameRu())
                            .en(companyEntity.getNameEn()).build())
            .companyPhoneNumber(companyEntity.getPhone())
            .mobilePhoneNumber(companyEntity.getMobilePhone())
            .email(companyEntity.getEmail())
            .bin(companyEntity.getBin())
            .build()).collect(Collectors.toList());
  }

  @Override
  public void addCompany(String companyNameKk, String companyNameRu,
                         String companyNameEn, String companyPhoneNumber,
                         String mobilePhone, String bin,
                         String email, String username, String password) {
    CompanyEntity entity = new CompanyEntity();
    entity.setNameRu(companyNameRu);
    entity.setNameKk(companyNameKk);
    entity.setNameEn(companyNameEn);
    entity.setPhone(companyPhoneNumber);
    entity.setMobilePhone(mobilePhone);
    entity.setBin(bin);
    entity.setEmail(email);

    LoginEntity loginEntity = new LoginEntity();
    loginEntity.setUsername(username);
    loginEntity.setPassword(password);
    loginEntity.setCompanyEntity(entity);

    entity.setLoginEntity(loginEntity);

    log.info("Added new company: " + companyNameRu + " " + new Date());
    this.companyRepository.save(entity);
  }

  @Override
  public void editCompany(String companyNameKk, String companyNameRu,
                          String companyNameEn, String companyPhoneNumber,
                          String mobilePhone, String bin,
                          String email, String username, String password) {

  }
}
