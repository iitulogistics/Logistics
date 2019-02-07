package kz.logistic.pl.services.impl;

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

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public void setLocalizedMessageBuilderFactory(LocalizedMessageBuilderFactory localizedMessageBuilderFactory) {
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
                .password(shipperEntity.getLoginEntity().getPassword()).build()).collect(Collectors.toList());
    }

}