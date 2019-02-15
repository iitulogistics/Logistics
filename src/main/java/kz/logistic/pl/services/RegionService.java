package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.LocalizedMessage;
import kz.logistic.pl.models.pojos.Region;
import kz.logistic.pl.models.pojos.json.RegionJson;

import java.util.List;

public interface RegionService {

    List<Region> showAllRegions();

    String addRegion(String regionNameEn, String regionNameKk, String regionNameRu, Long countryId);

    String addRegionJson(RegionJson regionJson);

}
