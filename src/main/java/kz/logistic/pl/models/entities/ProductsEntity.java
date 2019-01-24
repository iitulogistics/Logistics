package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "products")
public class ProductsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    @Column(name = "product_name_kk")
    private String product_name_kk;
    @Column(name = "product_name_ru")
    private String product_name_ru;
    @Column(name = "product_name_en")
    private String product_name_en;
    @Column(name = "product_subcategory_id")
    private Integer product_category_id;
    @Column(name = "unique_id_number")
    private String unique_id_number;
    @Column(name = "serial_number")
    private String serial_number;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "size")
    private String size;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "price")
    private Integer price;
    @Column(name = "product_description")
    private String product_description;

}
