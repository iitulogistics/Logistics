package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {
    List<LoginEntity> findByCustomerEntityCustomerIdIsNotNull();

    ArrayList<LoginEntity> findByUsernameAndPassword(String username, String password);

    ArrayList<LoginEntity> findByUsername(String username);
}
