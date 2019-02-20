package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehoseRepository extends JpaRepository<WarehouseEntity, Long> {
}
