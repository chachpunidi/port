package ru.port.service;

import org.apache.commons.lang3.NotImplementedException;
import ru.port.model.ShipDto;

import java.util.List;

public class BaseShipServiceImpl implements ShipService {
    @Override
    public List<ShipDto> getShipByCode(String shipCode) {
        throw new NotImplementedException("еще не реализовано");
    }

    @Override
    public List<ShipDto> getShips() {
        throw new NotImplementedException("еще не реализовано");
    }

    @Override
    public void addShip(ShipDto shipDto) {
        throw new NotImplementedException("еще не реализовано");
    }

    @Override
    public boolean deleteShip(String shipCode) {
        throw new NotImplementedException("еще не реализовано");
    }
}
