package com.utkuakgungor.entity;

import java.math.BigDecimal;

public class UpdateSeatEntity {
    private long flightID;
    private int seatNumber;
    private BigDecimal price;

    public long getFlightID() {
        return flightID;
    }

    public void setFlightID(long flightID) {
        this.flightID = flightID;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
