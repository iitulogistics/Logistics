package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;


@Setter
@Getter
@Entity
@Table(name = "credit_card")
public class CreditCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ccId;

    @Column(name = "cc_number")
    private Integer creditCardNumber;

    @Column(name = "holder_name")
    private String holderName;

    @Column(name = "expire_date")
    private Date expireDate;

}
