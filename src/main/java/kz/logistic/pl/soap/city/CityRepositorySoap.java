package kz.logistic.pl.soap.city;

import kz.logistic.pl.models.entities.CityEntity;
import kz.logistic.pl.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.city.City;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CityRepositorySoap {
    private static final Map<Long, City> cityMap = new HashMap<>();

    private CityRepository cityRepo;

    @Autowired(required = false)
    public void setCityRepository(CityRepository cityRepo){ this.cityRepo = cityRepo; }

    @PostConstruct
    public void initData(){
        List<CityEntity> entities = this.cityRepo.findAll();
        entities.forEach(cityEntity -> {
            City city = new City();
            city.setId(cityEntity.getCityId());
            city.setCountryId(cityEntity.getCountryId());
            city.setRegionId(cityEntity.getRegionId());
            city.setCityNameEn(cityEntity.getCityNameEn());
            city.setCityNameRu(cityEntity.getCityNameRu());
            city.setCityNameKk(cityEntity.getCityNameKk());


            cityMap.put(city.getId(), city);

        });
    }

    public City findCityId(Long id){return cityMap.get(id);}

    public City addCity(Long countryId, Long regionId, String nameKk, String nameRu, String nameEn){
        for(Long key : cityMap.keySet()){
            City city = cityMap.get(key);
            if(city.getCountryId() == countryId
                && city.getRegionId() == regionId
                && city.getCityNameEn().equals(nameKk)
                && city.getCityNameRu().equals(nameRu)
                && city.getCityNameKk().equals(nameEn))
                return null;
        }

        CityEntity cityEntity = new CityEntity();

        cityEntity.setCountryId(countryId);
        cityEntity.setRegionId(regionId);
        cityEntity.setCityNameEn(nameKk);
        cityEntity.setCityNameRu(nameRu);
        cityEntity.setCityNameKk(nameEn);
        cityRepo.save(cityEntity);

        City city = new City();
        city.setCountryId(countryId);
        city.setRegionId(regionId);
        city.setCityNameEn(nameKk);
        city.setCityNameRu(nameRu);
        city.setCityNameKk(nameEn);
        city.setId(cityEntity.getCityId());

        cityMap.put(city.getId(), city);
        return city;
    }

    public City updateCity(Long id, String nameKk, String nameRu, String nameEn){
        City city = cityMap.get(id);
        city.setCityNameEn(nameKk);
        city.setCityNameRu(nameRu);
        city.setCityNameKk(nameEn);

        cityRepo.updateCityById(id, nameKk, nameRu, nameEn);

        return city;
    }

    public String deleteCityId(Long id){
        CityEntity cityEntity = this.cityRepo.findById(id).orElse(null);

        if(cityEntity != null){
            this.cityRepo.deleteById(id);
            return "id" + id + "удалён";
        }
        else{
            return "id" + id + "не удалён";
        }
    }

}
