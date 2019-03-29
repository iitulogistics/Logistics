package kz.logistic.pl.services;

import java.util.List;
import kz.logistic.pl.models.pojos.Region;
import kz.logistic.pl.models.pojos.impl.DefaultRegion;
import kz.logistic.pl.models.pojos.json.RegionJson;


public interface RegionService {

  List<Region> showAllRegions();

  String addRegion(String regionNameEn, String regionNameKk, String regionNameRu, Long countryId);

  String addRegionJson(RegionJson regionJson);

  DefaultRegion showRegion(Long regionId);

  String updateRegion(Long regionId, RegionJson regionJson);

  String deleteRegion(Long regionId);

}
