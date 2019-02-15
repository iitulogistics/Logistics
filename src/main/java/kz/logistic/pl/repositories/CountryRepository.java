package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    ArrayList<CountryEntity> findByCountryNameEn(String countryNameEn);

}
