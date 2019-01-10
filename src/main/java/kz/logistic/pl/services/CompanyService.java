package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.Company;

import java.util.List;

public interface CompanyService {

  List<Company> companies();

  //RequestParam
  void addCompany(String companyNameKk, String companyNameRu,
                  String companyNameEn, String companyPhoneNumber,
                  String mobilePhone, String bin,
                  String email, String username,
                  String password);

  void editCompany(String companyNameKk, String companyNameRu,
                   String companyNameEn, String companyPhoneNumber,
                   String mobilePhone, String bin,
                   String email, String username,
                   String password);

  //Json RequestBody nuzhno sdelat

}
