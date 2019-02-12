package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<RegionEntity, Long> {
}
