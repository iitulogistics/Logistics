package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long product_id;

    @Column(name = "product_name_kk")
    private String productNameKk;
    @Column(name = "product_name_ru")
    private String productNameRu;
    @Column(name = "product_name_en")
    private String productNameEn;
    @Column(name = "product_category_id")
    private Long productCategoryId;
    @Column(name = "unique_id_number")
    private Long uniqueIdNumber;
    @Column(name = "serial_number")
    private Long serialNumber;
    @Column(name = "manufacturer")
    private Long manufacturer;
    @Column(name = "size")
    private Long size;
    @Column(name = "weight")
    private Long weight;
    @Column(name = "price")
    private Long price;
}
