package kz.logistic.pl.services;

import java.util.List;

import kz.logistic.pl.models.pojos.SellerCompany;
import kz.logistic.pl.models.pojos.impl.DefaultSellerCompany;
import kz.logistic.pl.models.pojos.json.SellerCompanyJson;

public interface SellerCompanyService {

    //Показать всех продавцов
    List<SellerCompany> showAllSellerCompanies();

    boolean exists(String username);

    DefaultSellerCompany showSellerCompany(Long sellerCompanyId);

    //Добавить нового продавца
    String addSellerCompany(String sellerCompanyNameKk, String sellerCompanyNameRu,
                            String sellerCompanyNameEn, String sellerCompanyPhone,
                            String sellerCompanyMobilePhone, String sellerCompanyBin,
                            String sellerCompanyEmail, String username, String password,
                            Long sellerCompanyCategoryId);

    String addSellerCompanyJson(SellerCompanyJson sellerCompanyJson);

    //Изменить данные продавца
    String updateSellerCompany(Long sellerCompanyId, SellerCompanyJson sellerCompanyJson);

    String deleteSellerCompany(Long sellerCompanyId);


}
