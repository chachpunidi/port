package ru.port.service;

import ru.port.model.ShipDto;

public interface ShipService {
    ShipDto getShipByCode(String shipCode);
}
