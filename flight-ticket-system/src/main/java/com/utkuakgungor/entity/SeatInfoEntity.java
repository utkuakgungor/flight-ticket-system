package com.utkuakgungor.entity;

import java.math.BigDecimal;

public class SeatInfoEntity {
    private int seatNumber;
    private BigDecimal price;

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
