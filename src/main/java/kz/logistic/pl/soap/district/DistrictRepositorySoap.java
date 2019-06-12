package kz.logistic.pl.soap.district;

import kz.logistic.pl.models.entities.DistrictEntity;
import kz.logistic.pl.repositories.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.district.District;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class DistrictRepositorySoap {
    private static final Map<Long, District> districtMap = new HashMap<>();

    private DistrictRepository districtRepository;

    @Autowired
    public void setDistrictRepository(DistrictRepository districtRepository){this.districtRepository=districtRepository;}

    @PostConstruct
    public void initData(){
        List<DistrictEntity> entities = this.districtRepository.findAll();
        entities.forEach(districtEntity -> {
            District district = new District();
            district.setId(districtEntity.getCityId());
            district.setCityId(districtEntity.getCityId());
            district.setRegionId(districtEntity.getRegionId());
            district.setDistrictNameEn(districtEntity.getDistrictNameEn());
            district.setDistrictNameRu(districtEntity.getDistrictNameRu());
            district.setDistrictNameKk(districtEntity.getDistrictNameKk());
            districtMap.put(district.getId(), district);

        });
    }

    public District findDistrictId(Long id){
        return districtMap.get(id);
    }

    public District addDistrict(Long regionId, Long cityId, String nameKk, String nameRu, String nameEn){
        for(Long key : districtMap.keySet()){
            District district = districtMap.get(key);
            if(district.getCityId() == cityId
                && district.getRegionId() == regionId
                && district.getDistrictNameKk().equals(nameKk)
                && district.getDistrictNameRu().equals(nameRu)
                && district.getDistrictNameEn().equals(nameEn))
                return null;
        }
        DistrictEntity districtEntity = new DistrictEntity();

        districtEntity.setRegionId(regionId);
        districtEntity.setCityId(cityId);
        districtEntity.setDistrictNameEn(nameEn);
        districtEntity.setDistrictNameRu(nameRu);
        districtEntity.setDistrictNameKk(nameKk);

        districtRepository.save(districtEntity);

        District district = new District();
        district.setRegionId(regionId);
        district.setCityId(cityId);
        district.setDistrictNameEn(nameEn);
        district.setDistrictNameRu(nameRu);
        district.setDistrictNameKk(nameKk);
        district.setId(districtEntity.getCityId());

        districtMap.put(district.getId(), district);
        return district;
    }

    public District updateDistrict(Long id, String nameKk, String nameRu, String nameEn){

        District district = districtMap.get(id);
        district.setDistrictNameEn(nameEn);
        district.setDistrictNameRu(nameRu);
        district.setDistrictNameKk(nameKk);
        districtRepository.updateDistrictById(id, nameEn, nameRu, nameKk);
        return district;
    }

    public String deleteDistrictId(Long id){
        DistrictEntity districtEntity = this.districtRepository.findById(id).orElse(null);
        if(districtEntity!=null){
            this.districtRepository.deleteById(id);
            return "id "+id+" удален";
        }

        else{
            return "id" + id + "не удалён";
        }
    }

}
