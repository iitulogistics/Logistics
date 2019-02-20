package kz.logistic.pl.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "products_category")
public class ProductsCategoryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productCategoryId;
  @Column(name = "category_name_kk")
  private String categoryNameKk;
  @Column(name = "category_name_ru")
  private String categoryNameRu;
  @Column(name = "category_name_en")
  private String categoryNameEn;
  @Column(name = "add_info")
  private String addInfo;
}
