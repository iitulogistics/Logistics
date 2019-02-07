package kz.logistic.pl.services;


import kz.logistic.pl.models.pojos.SellerCompany;
import kz.logistic.pl.models.pojos.json.SellerCompanyJson;

import java.util.List;

public interface SellerCompanyService {

    //Показать всех продавцов
    List<SellerCompany> showAllSellerCompanies();

    //Добавить нового продавца
    void addSellerCompany(String sellerCompanyNameKk, String sellerCompanyNameRu,
                          String sellerCompanyNameEn, String sellerCompanyPhone,
                          String sellerCompanyMobilePhone, String sellerCompanyBin,
                          String sellerCompanyEmail, String username, String password);

    //Изменить данные продавца
    void updateSellerCompany(String sellerCompanyNameKk, String sellerCompanyNameRu,
                             String sellerCompanyNameEn, String sellerCompanyPhone,
                             String sellerCompanyMobilePhone, String sellerCompanyBin,
                             String sellerCompanyEmail, long sellerCategoryId);

    void addSellerCompanyJson(SellerCompanyJson sellerCompanyJson);

}
