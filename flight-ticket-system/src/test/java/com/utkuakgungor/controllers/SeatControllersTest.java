package com.utkuakgungor.controllers;

import com.utkuakgungor.entity.*;
import com.utkuakgungor.repository.FlightRepository;
import com.utkuakgungor.repository.SeatRepository;
import com.utkuakgungor.service.impl.SeatImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SeatControllersTest {

    private FlightRepository flightRepository;
    private SeatRepository seatRepository;

    @BeforeEach
    void setUp() {
        flightRepository=mock(FlightRepository.class);
        seatRepository=mock(SeatRepository.class);
    }

    @Test
    void addSeat() {
        AddSeatEntity addSeatEntity=new AddSeatEntity();
        addSeatEntity.setSeatNumber(10);
        addSeatEntity.setFlightID(1L);
        addSeatEntity.setPrice(BigDecimal.ONE);
        FlightEntity flightEntity=new FlightEntity();
        flightEntity.setFlightID(1L);
        flightEntity.setName("Test");
        flightEntity.setCapacity(10);
        flightEntity.setDescription("Test");
        when(flightRepository.findByFlightID(1L)).thenReturn(flightEntity);
        SeatImpl seatImpl = new SeatImpl(seatRepository,flightRepository);
        SeatControllers seatControllers = new SeatControllers(seatImpl);
        seatControllers.addSeat(addSeatEntity);
    }

    @Test
    void updateSeat() {
        UpdateSeatEntity updateSeatEntity=new UpdateSeatEntity();
        updateSeatEntity.setSeatNumber(10);
        updateSeatEntity.setFlightID(1L);
        updateSeatEntity.setPrice(BigDecimal.ONE);
        SeatEntity seatEntity=new SeatEntity();
        seatEntity.setFlightID(1L);
        seatEntity.setAvailability(true);
        seatEntity.setSeatNumber(10);
        when(seatRepository.findByFlightIDAndSeatNumber(1L,10)).thenReturn(seatEntity);
        SeatImpl seatImpl = new SeatImpl(seatRepository,flightRepository);
        SeatControllers seatControllers = new SeatControllers(seatImpl);
        seatControllers.updateSeat(updateSeatEntity);
    }

    @Test
    void removeSeat() {
        RemoveSeatEntity removeSeatEntity=new RemoveSeatEntity();
        removeSeatEntity.setSeatNumber(10);
        removeSeatEntity.setFlightID(1L);
        SeatEntity seatEntity=new SeatEntity();
        seatEntity.setFlightID(1L);
        seatEntity.setAvailability(true);
        seatEntity.setSeatNumber(10);
        when(seatRepository.findByFlightIDAndSeatNumber(1L,10)).thenReturn(seatEntity);
        SeatImpl seatImpl = new SeatImpl(seatRepository,flightRepository);
        SeatControllers seatControllers = new SeatControllers(seatImpl);
        seatControllers.removeSeat(removeSeatEntity);
    }
}