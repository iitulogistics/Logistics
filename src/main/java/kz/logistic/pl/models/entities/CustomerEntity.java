package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Column(name = "login_entity_id")
    private Long loginEntityId;
    @Column(name = "customer_name_kk")
    private String customerNameKk;
    @Column(name = "customer_name_ru")
    private String customerNameRu;
    @Column(name = "customer_name_en")
    private String customerNameEn;
    @Column(name = "iin_or_bin")
    private String iinOrBin;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "mobile_phone")
    private String mobilePhone;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "add_info")
    private String addInfo;

    @OneToOne(mappedBy = "customerEntity", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private LoginEntity loginEntity;

}
