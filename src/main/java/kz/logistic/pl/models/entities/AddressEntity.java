package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(name = "district")
    private String district;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @Column(name = "flat")
    private int flat;

}