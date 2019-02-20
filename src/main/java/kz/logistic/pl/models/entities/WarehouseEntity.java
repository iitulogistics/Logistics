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
@Table(name = "warehouse")
public class WarehouseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long warehouseId;
  @Column(name = "wh_name_kk")
  private String whNameKk;
  @Column(name = "wh_name_ru")
  private String whnNameRu;
  @Column(name = "wh_name_en")
  private String whNameEn;

  @Column(name = "seller_company_id")
  private Long sellerCompanyId;
  @Column(name = "address_id")
  private Long addressId;
  @Column(name = "add_info")
  private String addInfo;
}
