package kz.logistic.pl.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import kz.logistic.pl.utils.ReturnMessage;
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
    private ReturnMessage returnMessage;

    @Autowired(required = false)
    public void setReturnMessage(ReturnMessage returnMessage) {
        this.returnMessage = returnMessage;
    }
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
    public boolean exists(String username) {
        LoginEntity loginEntity = this.loginRepository.findByUsername(username).orElse(null);
        return loginEntity != null;
    }

    @Override
    public String addShipperJson(ShipperJson shipperJson) {
        if (exists(shipperJson.getUsername())) {
            return java.text.MessageFormat.format(returnMessage.getShipperAddError(), shipperJson.getShipperNameEn());
        }
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(shipperJson.getUsername());
        loginEntity.setPassword(shipperJson.getPassword());

        ShipperEntity shipperEntity = new ShipperEntity();
        shipperEntity.setBin(shipperJson.getBin());
        shipperEntity.setAddress(shipperJson.getAddress());
        shipperEntity.setEmail(shipperJson.getEmail());
        shipperEntity.setPhoneNumber(shipperJson.getPhoneNumber());
        shipperEntity.setShipperNameKk(shipperJson.getShipperNameKk());
        shipperEntity.setShipperNameRu(shipperJson.getShipperNameRu());
        shipperEntity.setShipperNameEn(shipperJson.getShipperNameEn());
        shipperRepository.save(shipperEntity);
        loginEntity.setShipperEntity(shipperEntity);
        loginRepository.save(loginEntity);
        log.info("New shipper added, username: " + shipperJson.getUsername() + ". Time: " + new Date());

        return java.text.MessageFormat.format(returnMessage.getShipperAddSuccess(), shipperJson.getShipperNameEn());

    }

    @Override
    public String addShipper(String username,
                             String password,
                             String shipperNameKk,
                             String shipperNameRu,
                             String shipperNameEn,
                             String phoneNumber,
                             String bin,
                             String email,
                             String address) {
        if (exists(username)) {
            return java.text.MessageFormat.format(returnMessage.getShipperAddError(), shipperNameEn);

        }
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(username);
        loginEntity.setPassword(password);

        ShipperEntity shipperEntity = new ShipperEntity();
        shipperEntity.setShipperNameKk(shipperNameKk);
        shipperEntity.setShipperNameRu(shipperNameRu);
        shipperEntity.setShipperNameEn(shipperNameEn);
        shipperEntity.setAddress(address);
        shipperEntity.setBin(bin);
        shipperEntity.setEmail(email);
        shipperEntity.setPhoneNumber(phoneNumber);
        shipperRepository.save(shipperEntity);
        loginEntity.setShipperEntity(shipperEntity);
        loginRepository.save(loginEntity);
        log.info("New shipper added, username: " + username + ". Time: " + new Date());
        return java.text.MessageFormat.format(returnMessage.getShipperAddSuccess(), shipperNameEn);

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
            .bin(shipperEntity.getBin())
            .email(shipperEntity.getEmail())
            .address(shipperEntity.getAddress())
            .phoneNumber(shipperEntity.getPhoneNumber())
            .mobilePhone(shipperEntity.getMobilePhone())
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
                .ru(shipperEntity.getShipperNameRu()).build())
            .bin(shipperEntity.getBin())
            .email(shipperEntity.getEmail())
            .address(shipperEntity.getAddress())
            .phoneNumber(shipperEntity.getPhoneNumber())
            .mobilePhone(shipperEntity.getMobilePhone())
            .password(shipperEntity.getLoginEntity().getPassword())
            .build();
    }

    @Override
    public String updateShipper(Long shipperId, ShipperJson shipperJson) {
        ShipperEntity shipperEntity = this.shipperRepository.findById(shipperId).orElse(null);
        LoginEntity loginEntity = this.loginRepository.findById(shipperEntity.getLoginEntity().getLoginId()).orElse(null);

        if (Objects.nonNull(shipperEntity)) {
            if (shipperJson.getAddress() != null) {
                shipperEntity.setAddress(shipperJson.getAddress());
            }
            if (shipperJson.getBin() != null) {
                shipperEntity.setBin(shipperJson.getBin());
            }
            if (shipperJson.getPassword() != null) {
                loginEntity.setPassword(shipperJson.getPassword());
            }
            if (shipperJson.getEmail() != null) {
                shipperEntity.setEmail(shipperJson.getEmail());
            }
            if (shipperJson.getPhoneNumber() != null) {
                shipperEntity.setPhoneNumber(shipperJson.getPhoneNumber());
            }
            if (shipperJson.getShipperNameEn() != null) {
                shipperEntity.setShipperNameEn(shipperJson.getShipperNameEn());
            }
            if (shipperJson.getShipperNameKk() != null) {
                shipperEntity.setShipperNameKk(shipperJson.getShipperNameKk());
            }
            if (shipperJson.getShipperNameRu() != null) {
                shipperEntity.setShipperNameRu(shipperJson.getShipperNameRu());
            }
            this.loginRepository.save(loginEntity);
            this.shipperRepository.save(shipperEntity);
            log.info("Updated " + shipperId + " shipper " + new Date());
            return java.text.MessageFormat.format(returnMessage.getShipperUpdateSuccess(), shipperEntity.getShipperNameEn());

        } else {
            return java.text.MessageFormat.format(returnMessage.getShipperUpdateError(), shipperEntity.getShipperNameEn());

        }
    }

    @Override
    public String deleteShipper(Long shipperId) {
        ShipperEntity shipperEntity = this.shipperRepository.findById(shipperId).orElse(null);
        LoginEntity loginEntity = this.loginRepository.findById(shipperEntity.getLoginEntity().getLoginId()).orElse(null);
        this.loginRepository.deleteById(loginEntity.getLoginId());
        this.shipperRepository.deleteById(shipperEntity.getShipperId());
        return java.text.MessageFormat.format(returnMessage.getShipperDeleteSuccess(), shipperEntity.getShipperNameEn());

    }
}
