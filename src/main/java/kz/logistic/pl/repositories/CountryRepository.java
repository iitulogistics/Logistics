package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.CountryEntity;
import kz.logistic.pl.models.pojos.LocalizedMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    ArrayList<CountryEntity> findByCountryNameEn(String countryNameEn);

}
