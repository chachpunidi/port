package ru.port;

import ru.port.model.ShipDto;
import ru.port.service.InMemoryShipServiceImpl;
import ru.port.service.ShipService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.function.Consumer;

@Path("/ships/")     // аннотация из jax-rs привязывает все методы класса к адерсу {address-to-services}/aircrafts
@Produces(MediaType.APPLICATION_JSON) // аннотация из jax-rs говорит во что преобразововать отдаваемые java объекты.
@Singleton
// Чтоб каждый раз не создавался новый инстанс ShipRS. Переиспользуем инстанс который создался при первом обращении
public class ShipRS {

    private ShipService shipService = new InMemoryShipServiceImpl();


    @GET
    @Path("/{shipCode}")
    public List<ShipDto> getShipByCode(@PathParam("shipCode") String shipCode) {
        return shipService.getShipByCode(shipCode);
    }

    @GET
    public List<ShipDto> getShips() {
        return shipService.getShips();
    }

    @POST
    public void addShip(ShipDto shipDto) {
        shipService.addShip(shipDto);
    }

    @DELETE
    @Path("/{shipCode}")
    public boolean deleteShip(@PathParam("shipCode") String shipCode) {
        return shipService.deleteShip(shipCode);
    }
}
