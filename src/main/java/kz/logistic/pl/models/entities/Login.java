package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long login_id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "shipper_id")
    private Long shipperId;
    @Column(name = "company_id")
    private Long companyId;
    @Column(name = "seller_company_id")
    private Long sellerCompanyId;
    @Column(name = "seller_id")
    private String sellerId;
}
