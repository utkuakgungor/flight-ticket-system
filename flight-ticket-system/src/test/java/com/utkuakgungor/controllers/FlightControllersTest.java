package com.utkuakgungor.controllers;

import com.utkuakgungor.entity.*;
import com.utkuakgungor.repository.FlightRepository;
import com.utkuakgungor.repository.SeatRepository;
import com.utkuakgungor.service.impl.FlightImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FlightControllersTest {

    private FlightRepository flightRepository;
    private SeatRepository seatRepository;

    @BeforeEach
    void setUp() {
        flightRepository=mock(FlightRepository.class);
        seatRepository=mock(SeatRepository.class);
    }

    @Test
    void addFlight() {
        AddFlightEntity addFlightEntity=new AddFlightEntity();
        addFlightEntity.setFlightID(1L);
        addFlightEntity.setFlightName("Test");
        addFlightEntity.setCapacity(10);
        addFlightEntity.setDescription("Test");
        when(flightRepository.existsByFlightID(1L)).thenReturn(false);
        when(flightRepository.existsByName("Test")).thenReturn(false);
        FlightImpl flightImpl=new FlightImpl(flightRepository,seatRepository);
        FlightControllers flightControllers=new FlightControllers(flightImpl);
        flightControllers.addFlight(addFlightEntity);
    }

    @Test
    void updateFlight() {
        UpdateFlightEntity updateFlightEntity=new UpdateFlightEntity();
        updateFlightEntity.setFlightID(1L);
        updateFlightEntity.setCapacity(10);
        updateFlightEntity.setDescription("Test");
        FlightEntity flightEntity=new FlightEntity();
        flightEntity.setFlightID(1L);
        flightEntity.setName("Test");
        flightEntity.setCapacity(10);
        flightEntity.setDescription("Test");
        when(flightRepository.findByFlightID(1L)).thenReturn(flightEntity);
        when(seatRepository.findBiggestSeatNumber(1L)).thenReturn(9);
        FlightImpl flightImpl=new FlightImpl(flightRepository,seatRepository);
        FlightControllers flightControllers=new FlightControllers(flightImpl);
        flightControllers.updateFlight(updateFlightEntity);
    }

    @Test
    void removeFlight() {
        FlightEntity flightEntity=new FlightEntity();
        flightEntity.setFlightID(1L);
        flightEntity.setName("Test");
        flightEntity.setCapacity(10);
        flightEntity.setDescription("Test");
        when(flightRepository.findByFlightID(1L)).thenReturn(flightEntity);
        FlightImpl flightImpl=new FlightImpl(flightRepository,seatRepository);
        FlightControllers flightControllers=new FlightControllers(flightImpl);
        flightControllers.removeFlight(1L);
    }

    @Test
    void retrieveFlightInfo() {
        FlightEntity flightEntity=new FlightEntity();
        flightEntity.setFlightID(1L);
        flightEntity.setName("Test");
        flightEntity.setCapacity(10);
        flightEntity.setDescription("Test");
        List<SeatEntity> seatEntityList=new ArrayList<>();
        SeatEntity seatEntity=new SeatEntity();
        seatEntity.setAvailability(true);
        seatEntity.setPrice(BigDecimal.ONE);
        seatEntity.setSeatNumber(10);
        seatEntity.setFlightID(1L);
        seatEntityList.add(seatEntity);
        when(seatRepository.findByFlightIDAndAvailabilityTrue(1L)).thenReturn(seatEntityList);
        when(flightRepository.findByFlightID(1L)).thenReturn(flightEntity);
        FlightImpl flightImpl=new FlightImpl(flightRepository,seatRepository);
        FlightControllers flightControllers=new FlightControllers(flightImpl);
        flightControllers.retrieveFlightInfo(1L);
    }
}