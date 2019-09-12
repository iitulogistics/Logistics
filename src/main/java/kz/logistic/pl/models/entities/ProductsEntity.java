package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;


@Setter
@Getter
@Entity
@Table(name = "products")
@Document(indexName = "prod_indexes", type = "products")
public class ProductsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(name = "product_name_kk")
    private String productNameKk;
    @Column(name = "productNameRu")
    private String productNameRu;
    @Column(name = "product_name_en")
    private String productNameEn;
    @Column(name = "product_subcategory_id")
    private Long productSubcategoryId;
    @Column(name = "product_category_id")
    private Long productCategoryId;
    @Column(name = "unique_id_number")
    private String uniqueIdNumber;
    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "size")
    private String size;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "price")
    private Integer price;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "seller_company_id")
    private Long sellerCompanyId;
    @Column(name = "special_characteristic_id")
    private Long specialCharacteristicId;
    @Column(name = "products_img")
    private String productsImg;
}
