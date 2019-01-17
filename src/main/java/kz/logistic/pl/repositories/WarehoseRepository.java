package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehoseRepository extends JpaRepository<Warehouse, Long> {
}
