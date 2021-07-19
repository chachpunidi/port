package ru.port.service;

import com.github.javafaker.Faker;
import ru.port.model.ShipDto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InMemoryShipServiceImpl implements ShipService {
    List<ShipDto> ships;

    public InMemoryShipServiceImpl() {
        ships = new ArrayList<>();

        Faker faker = new Faker();
        for (int i = 1; i <= 100; i++) {
            ShipDto ship = new ShipDto();
            ship.setName(faker.funnyName().name());
            ship.setModel(faker.color().name());
            ship.setShipsCode(i + "");

            ships.add(ship);
        }
    }

    public List<ShipDto> getShipByCode(String shipCode) {
        List<ShipDto> targetShip = new ArrayList<>();
        for (ShipDto ship : ships) {
            if (ship.getShipsCode().equals(shipCode)) {
                targetShip.add(ship);
            }
        }
        return targetShip;
    }

    @Override
    public List<ShipDto> getShips() {
        return ships;
    }

    @Override
    public void addShip(ShipDto shipDto) {
        ships.add(shipDto);
    }

    @Override
    public boolean deleteShip(String shipCode) {

        Iterator<ShipDto> iter = ships.iterator();
        while (iter.hasNext()) {
            ShipDto ship = iter.next();
            if (ship.getShipsCode().equals(shipCode)) {
                iter.remove();
            }
        }
        return true;
    }
}
