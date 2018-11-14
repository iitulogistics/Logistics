package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.Company;

import java.util.List;

public interface CompanyService {

  List<Company> companies();

  void addCompany(String companyNameKk, String companyNameRu,
                  String companyNameEn, String companyPhoneNumber);

}
