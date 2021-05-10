package ru.port;

import ru.port.model.ShipDto;
import ru.port.service.InMemoryShipServiceImpl;
import ru.port.service.ShipService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ships/")     // аннотация из jax-rs привязывает все методы класса к адерсу {address-to-services}/aircrafts
@Produces(MediaType.APPLICATION_JSON) // аннотация из jax-rs говорит во что преобразововать отдаваемые java объекты.
public class ShipRS {

    private ShipService shipService = new InMemoryShipServiceImpl();

    @GET
    @Path("/{shipCode}")
    public ShipDto getShipByCode(@PathParam("shipCode") String shipCode) {
       return shipService.getShipByCode(shipCode);
    }

}
