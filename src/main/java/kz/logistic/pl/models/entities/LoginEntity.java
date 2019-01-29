package kz.logistic.pl.models.entities;

import kz.logistic.pl.models.pojos.SellerCompany;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "login")
public class LoginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long login_id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "roles_id")
    private Integer roles_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_company_id")
    private SellerCompanyEntity sellerCompanyEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    @Column(name = "shipper_id")
    private Long shipper_id;
}
