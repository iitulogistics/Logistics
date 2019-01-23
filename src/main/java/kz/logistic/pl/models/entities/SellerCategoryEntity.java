package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "seller_category")
public class SellerCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seller_category_id;
    @Column(name = "category_name_kk")
    private String category_name_kk;
    @Column(name = "category_name_ru")
    private String category_name_ru;
    @Column(name = "category_name_en")
    private String category_name_en;
    @Column(name = "add_info")
    private String add_info;
}
