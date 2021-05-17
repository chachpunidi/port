package ru.port.service;

import ru.port.model.ShipDto;

import java.util.ArrayList;
import java.util.List;

public class InMemoryShipServiceImpl implements ShipService{
    List<ShipDto> ships;

    public  InMemoryShipServiceImpl() {
        ships = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            ShipDto ship = new ShipDto();
            ship.setName("hh");
            ship.setModel("kk");
            ship.setShipsCode(i + "");

            ships.add(ship);
        }
    }

    public ShipDto getShipByCode(String shipCode) {
        for (ShipDto ship : ships) {
            if (ship.getShipsCode().equals(shipCode)) {
                return ship;
            }
        }
        return null;
    }
}
