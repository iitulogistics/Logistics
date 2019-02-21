package kz.logistic.pl.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import kz.logistic.pl.models.entities.LoginEntity;
import kz.logistic.pl.models.entities.ShipperEntity;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.pojos.Shipper;
import kz.logistic.pl.models.pojos.impl.DefaultShipper;
import kz.logistic.pl.models.pojos.json.ShipperJson;
import kz.logistic.pl.repositories.LoginRepository;
import kz.logistic.pl.repositories.ShipperRepository;
import kz.logistic.pl.services.ShipperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;



@Slf4j
public class DefaultShipperService implements ShipperService {

  private ShipperRepository shipperRepository;
  private LoginRepository loginRepository;
  private LocalizedMessageBuilderFactory localizedMessageBuilderFactory;

  @Autowired
  void setShipperRepository(ShipperRepository shipperRepository) {
    this.shipperRepository = shipperRepository;
  }

  @Autowired
  void setLoginRepository(LoginRepository loginRepository) {
    this.loginRepository = loginRepository;
  }

  @Autowired
  public void setLocalizedMessageBuilderFactory(
    LocalizedMessageBuilderFactory localizedMessageBuilderFactory) {
    this.localizedMessageBuilderFactory = localizedMessageBuilderFactory;
  }

  @Override
  public void addShipperJson(ShipperJson shipperJson) {
    LoginEntity loginEntity = new LoginEntity();
    loginEntity.setUsername(shipperJson.getUsername());
    loginEntity.setPassword(shipperJson.getPassword());

    ShipperEntity shipperEntity = new ShipperEntity();
    shipperRepository.save(shipperEntity);
    loginEntity.setShipperEntity(shipperEntity);
    loginRepository.save(loginEntity);
    log.info("New shipper added, username: " + shipperJson.getUsername() + ". Time: " + new Date());
  }

  @Override
  public void addShipper(String username, String password) {
    LoginEntity loginEntity = new LoginEntity();
    loginEntity.setUsername(username);
    loginEntity.setPassword(password);

    ShipperEntity shipperEntity = new ShipperEntity();
    shipperRepository.save(shipperEntity);
    loginEntity.setShipperEntity(shipperEntity);
    loginRepository.save(loginEntity);
    log.info("New shipper added, username: " + username + ". Time: " + new Date());
  }

  @Override
  public List<Shipper> showAllShippers() {
    List<ShipperEntity> shipperEntities = this.shipperRepository.findAll();
    return shipperEntities.stream().map(shipperEntity -> DefaultShipper.builder()
      .shipperId(shipperEntity.getShipperId())
      .shipperName(localizedMessageBuilderFactory.builder()
        .kk(shipperEntity.getShipperNameKk())
        .ru(shipperEntity.getShipperNameRu())
        .en(shipperEntity.getShipperNameEn()).build())
      .username(shipperEntity.getLoginEntity().getUsername())
      .password(shipperEntity.getLoginEntity().getPassword())
//      .bin(shipperEntity.getBin())
//      .email(shipperEntity.getEmail())
//      .address(shipperEntity.getAddress())
//      .phoneNumber(shipperEntity.getPhoneNumber())
//      .mobilePhone(shipperEntity.getMobilePhone())
      .build()).collect(Collectors.toList());
  }

  @Override
  public DefaultShipper showShipper(Long shipperId) {
    ShipperEntity shipperEntity = this.shipperRepository.findById(shipperId).orElse(null);
    return DefaultShipper.builder()
      .shipperId(shipperEntity.getShipperId())
      .username(shipperEntity.getLoginEntity().getUsername())
      .shipperName(localizedMessageBuilderFactory.builder()
      .en(shipperEntity.getShipperNameEn())
      .kk(shipperEntity.getShipperNameKk())
      .ru(shipperEntity.getShipperNameRu()).build()).build();
  }

  @Override
  public String updateShipper(Long shipperId, ShipperJson shipperJson) {
    ShipperEntity shipperEntity = this.shipperRepository.findById(shipperId).orElse(null);
    LoginEntity loginEntity = this.loginRepository.findById(shipperEntity.getLoginEntity().getLoginId()).orElse(null);

    if(Objects.nonNull(shipperEntity)){
      if (shipperJson.getAddress() != null){
        shipperEntity.setAddress(shipperJson.getAddress());
      }
      if(shipperJson.getBin() != null){
        shipperEntity.setBin(shipperJson.getBin());
      }
      if(shipperJson.getPassword() != null){
        loginEntity.setPassword(shipperJson.getPassword());
      }
      if(shipperJson.getEmail() != null){
        shipperEntity.setEmail(shipperJson.getEmail());
      }
      if(shipperJson.getMobilePhone() != null){
        shipperEntity.setMobilePhone(shipperJson.getMobilePhone());
      }
      if(shipperJson.getPhoneNumber() != null){
        shipperEntity.setPhoneNumber(shipperJson.getPhoneNumber());
      }
      if(shipperJson.getShipperNameEn() != null){
        shipperEntity.setShipperNameEn(shipperJson.getShipperNameEn());
      }
      if(shipperJson.getShipperNameKk() != null){
        shipperEntity.setShipperNameKk(shipperJson.getShipperNameKk());
      }
      if(shipperJson.getShipperNameRu() != null){
        shipperEntity.setShipperNameRu(shipperJson.getShipperNameRu());
      }
      this.loginRepository.save(loginEntity);
      this.shipperRepository.save(shipperEntity);
      log.info("Updated " + shipperId + " shipper " + new Date());
      return "Доставщик обнавлен";
    }else{
      return "Доставщика с таким ID не существует";
    }
  }

  @Override
  public String deleteShipper(Long shipperId) {
    ShipperEntity shipperEntity = this.shipperRepository.findById(shipperId).orElse(null);
    LoginEntity loginEntity = this.loginRepository.findById(shipperEntity.getLoginEntity().getLoginId()).orElse(null);
    this.loginRepository.deleteById(loginEntity.getLoginId());
    this.shipperRepository.deleteById(shipperEntity.getShipperId());
    return "Данные доставщика удалены";
  }
}