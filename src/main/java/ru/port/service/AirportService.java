package ru.port.service;
import ru.port.model.AirportDto;


import java.util.List;

public interface AirportService {
    List<AirportDto>  getAirportByCode(String airportCode);

    List<AirportDto> getAirports();

    void addAirport(AirportDto airportDto);

    boolean deleteAirport(String airportCode);

}
