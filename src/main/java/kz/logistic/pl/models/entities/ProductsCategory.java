package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "products_category")
public class ProductsCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_category_id;
    @Column(name = "category_name_kk")
    private String categoryNameKk;
    @Column(name = "category_name_ru")
    private String categoryNameRu;
    @Column(name = "category_name_en")
    private String categoryNameEn;
}
