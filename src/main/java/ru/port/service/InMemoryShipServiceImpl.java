package ru.port.service;

import ru.port.model.ShipDto;

public class InMemoryShipServiceImpl implements ShipService{

    public ShipDto getShipByCode(String shipCode) {
        ShipDto ship =new ShipDto();
        ship.setName("hh");
        ship.setModel("kk");
        ship.setShipsCode(shipCode);
        return ship;
    }



}
