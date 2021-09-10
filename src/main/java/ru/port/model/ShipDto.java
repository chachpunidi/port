package ru.port.model;

public class ShipDto implements AutoCloseable {

    private String shipCode;
    private String model;
    private Long range;

    public String getShipsCode() {
        return shipCode;
    }

    public void setShipsCode(String shipCode) {
        this.shipCode = shipCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getRange() {
        return range;
    }

    public void setRange(Long range) {
        this. range = range;
    }

    @Override
    public void close() throws Exception {

    }
}
