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
    private Long id;

    @Column(name = "mobile_phone")
    private String mobile_phone;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;

    @OneToOne(mappedBy = "customerEntity", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private LoginEntity loginEntity;
}
