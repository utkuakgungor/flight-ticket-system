package com.utkuakgungor.entity;

import java.util.List;

public class FlightInfoEntity {
    private String flightName;
    private String description;
    private List<SeatInfoEntity> availableSeats;

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SeatInfoEntity> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<SeatInfoEntity> availableSeats) {
        this.availableSeats = availableSeats;
    }
}
