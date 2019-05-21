package kz.logistic.pl.soap.region;

import kz.logistic.pl.models.entities.RegionEntity;
import kz.logistic.pl.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.region.Region;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RegionRepositorySoap {
    private static final Map<Long, Region> regMap = new HashMap<>();

    private RegionRepository regionRepository;

    @Autowired(required = false)
    public void setRegionRepository(RegionRepository regionRepository){ this.regionRepository = regionRepository; }

    @PostConstruct
    public void initData(){
        List<RegionEntity> entities = this.regionRepository.findAll();
        entities.forEach(regionEntity -> {
            Region region = new Region();
            region.setId(regionEntity.getRegionId());
            region.setRegionNameKk(regionEntity.getRegionNameKk());
            region.setRegionNameRu(regionEntity.getRegionNameRu());
            region.setRegionNameEn(regionEntity.getRegionNameEn());
            region.setCountryId(regionEntity.getCountryId());

            regMap.put(region.getId(), region);
        });
    }

    public Region addRegion(String RegionNameKk, String RegionNameRu, String RegionNameEn, Long countryId){
        for(Long key : regMap.keySet()){
            Region region = regMap.get(key);
            if(region.getRegionNameKk().equals(RegionNameKk) &&
            region.getRegionNameRu().equals(RegionNameRu) &&
            region.getRegionNameEn().equals(RegionNameEn) &&
            region.getCountryId() == countryId)
                return null;
        }

        RegionEntity regionEntity = new RegionEntity();

        regionEntity.setRegionNameKk(RegionNameKk);
        regionEntity.setRegionNameRu(RegionNameRu);
        regionEntity.setRegionNameEn(RegionNameEn);
        regionEntity.setCountryId(countryId);
        regionRepository.save(regionEntity);

        Region region = new Region();
        region.setRegionNameKk(RegionNameKk);
        region.setRegionNameRu(RegionNameRu);
        region.setRegionNameEn(RegionNameEn);
        region.setCountryId(countryId);

        regMap.put(region.getId(), region);
        return region;
    }

    public Region updateRegion(Long id, String RegionNameKk, String RegionNameRu, String RegionNameEn, Long countryId){
        Region region = regMap.get(id);

        region.setRegionNameKk(RegionNameKk);
        region.setRegionNameRu(RegionNameRu);
        region.setRegionNameEn(RegionNameEn);
        region.setCountryId(countryId);

        regionRepository.updateRegionById(id, RegionNameKk, RegionNameRu, RegionNameEn, countryId);

        return region;
    }

    public String deleteRegion(Long id){
        RegionEntity regionEntity = this.regionRepository.findById(id).orElse(null);

        if(regionEntity != null){
            this.regionRepository.deleteById(id);
            return "регион" + id + "удалён";
        }
        else{
            return "регион" + id + "не удалён";
        }
    }

    public Region findRegionId(Long id){return regMap.get(id);}

}
