package kz.logistic.pl.soap.seller_company;

import kz.logistic.pl.models.entities.SellerCompanyEntity;
import kz.logistic.pl.repositories.SellerCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.seller_company.SellerCompany;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SellerCompanyRepositorySoap {
  private static final Map<Long, SellerCompany> sellerMap = new HashMap<>();

  private SellerCompanyRepository sellerCompanyRepository;

  @Autowired(required = false)
  public void setSellerCompanyRepository(SellerCompanyRepository sellerCompanyRepository) {
    this.sellerCompanyRepository = sellerCompanyRepository;
  }

  @PostConstruct
  public void initData() {
    List<SellerCompanyEntity> entities = this.sellerCompanyRepository.findAll();
    entities.forEach(sellerCompanyEntity -> {
      SellerCompany sellerCompany = convertToSellerCompany(sellerCompanyEntity);

      sellerMap.put(sellerCompany.getSellerCompanyId(), sellerCompany);
    });
  }

  public SellerCompany findSellerCompanyId(Long id) {
    return sellerMap.get(id);
  }

  public SellerCompany addSellerCompany(String companyNameEn, String companyNameRu,
                                        String companyNameKk, String bin, String email, String mobilePhone,
                                        String phone, Long sellerCategoryId) {
    SellerCompanyEntity sellerCompanyEntity = new SellerCompanyEntity();
    sellerCompanyEntity.setCompanyNameEn(companyNameEn);
    sellerCompanyEntity.setCompanyNameRu(companyNameRu);
    sellerCompanyEntity.setCompanyNameKk(companyNameKk);
    sellerCompanyEntity.setBin(bin);
    sellerCompanyEntity.setEmail(email);
    sellerCompanyEntity.setMobilePhone(mobilePhone);
    sellerCompanyEntity.setPhone(phone);
    sellerCompanyEntity.setSellerCategoryId(sellerCategoryId);

    sellerCompanyRepository.save(sellerCompanyEntity);

    SellerCompany sellerCompany = convertToSellerCompany(sellerCompanyEntity);
    sellerMap.put(sellerCompany.getSellerCompanyId(), sellerCompany);

    return sellerCompany;
  }

  public SellerCompany updateSellerCompany(Long id, String companyNameEn, String companyNameRu,
                                           String companyNameKk, String bin, String email, String mobilePhone,
                                           String phone, Long sellerCategoryId) {
    SellerCompany sellerCompany = sellerMap.get(id);
    sellerCompany.setCompanyNameEn(companyNameEn);
    sellerCompany.setCompanyNameRu(companyNameRu);
    sellerCompany.setCompanyNameKk(companyNameKk);
    sellerCompany.setBin(bin);
    sellerCompany.setEmail(email);
    sellerCompany.setMobilePhone(mobilePhone);
    sellerCompany.setPhone(phone);
    sellerCompany.setSellerCategoryId(sellerCategoryId);

    sellerCompanyRepository.updateSellerCompanyById(id, companyNameEn, companyNameRu, companyNameKk,
      bin, email, mobilePhone, phone, sellerCategoryId);
    return sellerCompany;
  }

  public String deleteSellerCompany(Long id) {
    SellerCompanyEntity sellerCompanyEntity = this.sellerCompanyRepository.findById(id).orElse(null);

    if (sellerCompanyEntity != null) {
      this.sellerCompanyRepository.deleteById(id);
      return "id " + id + " удалён";
    } else {
      return "id " + id + " не удалён";
    }
  }

  private SellerCompany convertToSellerCompany(SellerCompanyEntity entity) {
    SellerCompany sellerCompany = new SellerCompany();
    sellerCompany.setCompanyNameEn(entity.getCompanyNameEn());
    sellerCompany.setCompanyNameRu(entity.getCompanyNameRu());
    sellerCompany.setCompanyNameKk(entity.getCompanyNameKk());
    sellerCompany.setSellerCompanyId(entity.getSellerCompanyId());
    sellerCompany.setBin(entity.getBin());
    sellerCompany.setEmail(entity.getEmail());
    sellerCompany.setMobilePhone(entity.getMobilePhone());
    sellerCompany.setPhone(entity.getPhone());
    sellerCompany.setSellerCategoryId(entity.getSellerCategoryId());

    return sellerCompany;
  }
}
