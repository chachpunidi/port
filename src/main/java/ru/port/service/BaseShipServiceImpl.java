package ru.port.service;

import ru.port.model.ShipDto;
import ru.port.repository.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseShipServiceImpl implements ShipService {

    public BaseShipServiceImpl(){

    }

    @Override
    public List<ShipDto> getShipByCode(String shipCode) {
        List<ShipDto> ships = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
            String someSgl = "select * " +
                    "from aircrafts a " +
                    "where a.aircraft_code = ?";
            PreparedStatement stmt = connection.prepareStatement(someSgl);
            stmt.setString(1, shipCode);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ShipDto ship = new ShipDto();
                ship.setShipsCode(rs.getString("aircraft_code"));
                ship.setModel(rs.getString("model"));
                ship.setRange(rs.getLong("range"));
                ships.add(ship);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DBManager.closeConnection(connection);
        }
        return ships;
    }

    @Override
    public List<ShipDto> getShips() {  List<ShipDto> ships = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
            String someSgl = "select * from aircrafts a " ;
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(someSgl);

            while (rs.next()) {
                ShipDto ship = new ShipDto();
                ship.setShipsCode(rs.getString("aircraft_code"));
                ship.setModel(rs.getString("model"));
                ship.setRange(rs.getLong("range"));
                ships.add(ship);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        finally {
            DBManager.closeConnection(connection);
        }
        return ships;


    }

    @Override
    public void addShip(ShipDto shipDto) {
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
            String modelJsonString = String.format(
                "{\"en\":\"%s\", \"ru\":\"%s\"}",
                shipDto.getModel(),
                shipDto.getModel()
            );
            String someSgl = "insert into aircrafts_data (aircraft_code, model, range) " +
                    " values (?,?::json,?)";
            PreparedStatement stmt = connection.prepareStatement(someSgl);
            stmt.setString(1, shipDto.getShipsCode());
            stmt.setString(2, modelJsonString);
            stmt.setLong(3, shipDto.getRange());
            stmt.executeUpdate();
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBManager.closeConnection(connection);
        }
    }

    @Override
    public boolean deleteShip(String shipCode) {
        String someSgl = "DELETE FROM aircrafts WHERE aircraft_code = ?";
        try(
            Connection connection = DBManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(someSgl)
        ) {
            stmt.setString(1, shipCode);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return true;



    }
}
