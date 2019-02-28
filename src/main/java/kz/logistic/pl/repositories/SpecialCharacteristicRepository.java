package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.SpecialCharacteristicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialCharacteristicRepository extends JpaRepository<SpecialCharacteristicEntity, Long> {
}
