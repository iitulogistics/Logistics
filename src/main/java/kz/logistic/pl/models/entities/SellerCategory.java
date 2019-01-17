package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "seller_category")
public class SellerCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "additional_info")
    private String additionalInfo;
}
