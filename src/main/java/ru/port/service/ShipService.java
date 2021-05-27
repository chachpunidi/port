package ru.port.service;

import ru.port.model.ShipDto;

import java.util.List;

public interface ShipService {

    List<ShipDto>  getShipByCode(String shipCode);

    List<ShipDto> getShips();

   void addShip(ShipDto shipDto);

   boolean deleteShip(String shipCode);

}
