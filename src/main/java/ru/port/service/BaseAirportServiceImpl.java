package ru.port.service;

import ru.port.model.AirportDto;

import ru.port.repository.DBManager;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class BaseAirportServiceImpl implements AirportService{
    public BaseAirportServiceImpl(){

    }

    @Override
    public List<AirportDto> getAirportByCode(String airportCode) {
        List<AirportDto> airports = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
            String someSgl = "select * " +
                    "from airports a " +
                    "where a.airport_code = ?";
            PreparedStatement stmt = connection.prepareStatement(someSgl);
            stmt.setString(1, airportCode);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String[] coordinates = strToCoordinates(rs.getString("coordinates"));
                AirportDto airport = new AirportDto();
                airport.setAirportCode(rs.getString("airport_code"));
                airport.setAirportName(rs.getString("airport_name"));
                airport.setCity(rs.getString("city"));
                airport.setLatitude(coordinates[0]);
                airport.setLongitude(coordinates[1]);
                airport.setTimeZone(rs.getString("timezone"));
                airports.add(airport);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DBManager.closeConnection(connection);
        }
        return airports;
    }

    @Override
    public List<AirportDto> getAirports() {  List<AirportDto> airports = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
            String someSgl = "select * from airports a " ;
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(someSgl);

            while (rs.next()) {
                AirportDto airport = new AirportDto();
                airport.setAirportCode(rs.getString("airport_code"));
                airport.setAirportName(rs.getString("airport_name"));
                airport.setCity(rs.getString("city"));
                airport.setTimeZone(rs.getString("timezone"));
                airports.add(airport);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        finally {
            DBManager.closeConnection(connection);
        }
        return airports;


    }

    @Override
    public void addAirport(AirportDto airportDto) {
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
            String modelJsonString = String.format(
                    "{\"en\":\"%s\", \"ru\":\"%s\"}",
                    airportDto.getAirportName(),
                    airportDto.getAirportName()
            );
            String someSgl = "insert into airports a (airport_code, airport_name, city, timezone) " +
                    " values (?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(someSgl);
            stmt.setString(1, airportDto.getAirportCode());
            stmt.setString(2, airportDto.getAirportName());
            stmt.setString(3, airportDto.getCity());
            stmt.setString(4, airportDto.getTimeZone());
            stmt.executeUpdate();
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBManager.closeConnection(connection);
        }
    }

    @Override
    public boolean deleteAirport(String airportCode) {
        String someSgl = "DELETE FROM airports WHERE airport_code = ?";
        try(
                Connection connection = DBManager.getConnection();
                PreparedStatement stmt = connection.prepareStatement(someSgl)
        ) {
            stmt.setString(1, airportCode);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private String[] strToCoordinates(String coordinates) {
        if (coordinates == null) {
            return null;
        }
        return coordinates
                .replace("(", "")
                .replace(")", "")
                .split(",");
    }

}
