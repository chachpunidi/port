package ru.port;

import ru.port.model.AirportDto;
import ru.port.service.BaseAirportServiceImpl;
import ru.port.service.AirportService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;



@Path("/airports/")     // аннотация из jax-rs привязывает все методы класса к адерсу {address-to-services}/aircrafts
@Produces(MediaType.APPLICATION_JSON) // аннотация из jax-rs говорит во что преобразововать отдаваемые java объекты.
@Singleton
public class PortRS {
    private AirportService airportService = new BaseAirportServiceImpl();

    @GET
    @Path("/{airportCode}")
    public List<AirportDto> getShipByCode(@PathParam("airportCode") String airportCode) throws SQLException {
        return airportService.getAirportByCode(airportCode);
    }

    @GET
    public List<AirportDto> getAirports() {
        return airportService.getAirports();
    }

    @POST
    public void addAirport(AirportDto airportDto) {
        airportService.addAirport(airportDto);
    }

    @DELETE
    @Path("/{airportCode}")
    public boolean deleteAirport(@PathParam("airportCode") String airportCode) {
        return airportService.deleteAirport(airportCode);
    }
}
