package kz.logistic.pl.repositories;

import java.util.ArrayList;
import java.util.List;
import kz.logistic.pl.models.entities.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {
    List<LoginEntity> findByCustomerEntityCustomerIdIsNotNull();

    LoginEntity findByUsernameAndPassword(String username, String password);

    LoginEntity findByUsername(String username);
}
