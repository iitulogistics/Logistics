package kz.logistic.pl.soap.shipper;


import kz.logistic.pl.models.entities.ShipperEntity;
import kz.logistic.pl.repositories.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.shipper.Shipper;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ShipperRepositorySoap {
  private static final Map<Long, Shipper> shipperMap = new HashMap<>();

  private ShipperRepository shipperRepository;

  @Autowired(required = false)
  public void setShipperRepository(ShipperRepository shipperRepository) {
    this.shipperRepository = shipperRepository;
  }

  @PostConstruct
  public void initData() {
    List<ShipperEntity> entities = this.shipperRepository.findAll();
    entities.forEach(shipperEntity -> {
      Shipper shipper = convertToShipper(shipperEntity);

      shipperMap.put(shipper.getShipperId(), shipper);
    });
  }

  public Shipper findShipperId(Long id) {
    return shipperMap.get(id);
  }

  public Shipper addShipper(String shipperNameEn, String shipperNameKk, String shipperNameRu,
                            String address, String mobilePhone, String phoneNumber, String email,
                            String bin) {
    ShipperEntity shippersEntity = new ShipperEntity();
    shippersEntity.setShipperNameEn(shipperNameEn);
    shippersEntity.setShipperNameKk(shipperNameKk);
    shippersEntity.setShipperNameRu(shipperNameRu);
    shippersEntity.setAddress(address);
    shippersEntity.setMobilePhone(mobilePhone);
    shippersEntity.setPhoneNumber(phoneNumber);
    shippersEntity.setEmail(email);
    shippersEntity.setBin(bin);

    shipperRepository.save(shippersEntity);

    Shipper shipper = convertToShipper(shippersEntity);
    shipperMap.put(shipper.getShipperId(), shipper);

    return shipper;
  }

  public Shipper updateShipper(long id, String shipperNameEn, String shipperNameKk, String shipperNameRu,
                               String address, String mobilePhone, String phoneNumber, String email,
                               String bin) {
    Shipper shipper = shipperMap.get(id);
    shipper.setShipperNameEn(shipperNameEn);
    shipper.setShipperNameKk(shipperNameKk);
    shipper.setShipperNameRu(shipperNameRu);
    shipper.setAddress(address);
    shipper.setMobilePhone(mobilePhone);
    shipper.setPhoneNumber(phoneNumber);
    shipper.setEmail(email);
    shipper.setBin(bin);

    shipperRepository.updateShipperById(id, shipperNameEn, shipperNameKk, shipperNameRu, address,
      mobilePhone, phoneNumber, email, bin);
    return shipper;
  }

  public String deleteShipper(Long id) {
    ShipperEntity shippersEntity = this.shipperRepository.findById(id).orElse(null);

    if (shippersEntity != null) {
      this.shipperRepository.deleteById(id);
      return "id " + id + " удалён";
    } else {
      return "id " + id + " не удалён";
    }
  }

  private Shipper convertToShipper(ShipperEntity entity) {
    Shipper shipper = new Shipper();
    shipper.setShipperId(entity.getShipperId());
    shipper.setShipperNameEn(entity.getShipperNameEn());
    shipper.setShipperNameKk(entity.getShipperNameKk());
    shipper.setShipperNameRu(entity.getShipperNameRu());
    shipper.setAddress(entity.getAddress());
    shipper.setMobilePhone(entity.getMobilePhone());
    shipper.setPhoneNumber(entity.getPhoneNumber());
    shipper.setEmail(entity.getEmail());
    shipper.setBin(shipper.getBin());

    return shipper;
  }
}
