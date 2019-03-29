package kz.logistic.pl.services;


import kz.logistic.pl.models.entities.DistrictEntity;
import kz.logistic.pl.models.pojos.District;
import kz.logistic.pl.models.pojos.impl.DefaultDistrict;
import kz.logistic.pl.models.pojos.json.DistrictJson;

import java.util.List;

public interface DistrictService {


  List<District> showAllDistricts();

  DefaultDistrict showDistrict(Long districtId);

  String addDistrict(String districtNameKk, String districtNameEn, String districtNameRu, Long regionId, Long cityid);

  String addDistrictJson(DistrictJson districtJson);

  String updateDistrict(Long districtId, DistrictJson districtJson);

  String deleteDistrict(Long districtId);

}
