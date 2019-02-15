package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "products_subcategory")
public class ProductsSubCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSubcategoryId;
    @Column(name = "subcategory_name_kk")
    private String subCategoryNameKk;
    @Column(name = "subcategory_name_ru")
    private String subCategoryNameRu;
    @Column(name = "subcategory_name_en")
    private String subCategoryNameEn;
    @Column(name = "product_category_id")
    private Long productCategoryId;
    @Column(name = "subcategory_add_info")
    private String subCategoryAddInfo;
}
