package com.utkuakgungor.controllers;

import com.utkuakgungor.entity.*;
import com.utkuakgungor.repository.PaymentRepository;
import com.utkuakgungor.repository.SeatRepository;
import com.utkuakgungor.service.impl.PaymentImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentControllersTest {
    private SeatRepository seatRepository;
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = mock(PaymentRepository.class);
        seatRepository = mock(SeatRepository.class);
    }

    @Test
    void pay() {
        PayEntity payEntity=new PayEntity();
        payEntity.setFlightID(1L);
        payEntity.setSeatNumber(10);
        SeatEntity seatEntity=new SeatEntity();
        seatEntity.setFlightID(1L);
        seatEntity.setAvailability(true);
        seatEntity.setSeatNumber(10);
        when(seatRepository.findByFlightIDAndSeatNumber(1L,10)).thenReturn(seatEntity);
        PaymentImpl paymentImpl = new PaymentImpl(seatRepository,paymentRepository);
        PaymentControllers paymentControllers = new PaymentControllers(paymentImpl);
        paymentControllers.pay(payEntity);
    }
}