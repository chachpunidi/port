package ru.port.service;

import ru.port.model.ShipDto;

import java.util.List;

public interface ShipService {

    ShipDto getShipByCode(String shipCode);

    List<ShipDto> getShips();
}
