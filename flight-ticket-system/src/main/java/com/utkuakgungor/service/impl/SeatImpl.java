package com.utkuakgungor.service.impl;

import com.utkuakgungor.entity.*;
import com.utkuakgungor.repository.FlightRepository;
import com.utkuakgungor.repository.SeatRepository;
import com.utkuakgungor.service.SeatInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SeatImpl implements SeatInterface {

    private final SeatRepository seatRepository;
    private final FlightRepository flightRepository;

    public SeatImpl(SeatRepository seatRepository, FlightRepository flightRepository) {
        this.seatRepository = seatRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public boolean addSeat(AddSeatEntity addSeatEntity) throws Exception {
        FlightEntity flightEntity = flightRepository.findByFlightID(addSeatEntity.getFlightID());
        if (flightEntity == null) {
            throw new Exception("Flight not found");
        }
        if (flightEntity.getCapacity() < addSeatEntity.getSeatNumber()) {
            throw new Exception("Seat number higher than flight capacity");
        }
        SeatEntity seatEntity = new SeatEntity();
        seatEntity.setPrice(addSeatEntity.getPrice());
        seatEntity.setSeatNumber(addSeatEntity.getSeatNumber());
        seatEntity.setFlightID(addSeatEntity.getFlightID());
        seatEntity.setAvailability(true);
        seatRepository.save(seatEntity);
        return true;
    }

    @Override
    public boolean updateSeat(UpdateSeatEntity updateSeatEntity) throws Exception {
        SeatEntity seatEntity = seatRepository.findByFlightIDAndSeatNumber(updateSeatEntity.getFlightID(), updateSeatEntity.getSeatNumber());
        if (seatEntity != null) {
            seatEntity.setPrice(updateSeatEntity.getPrice());
            seatRepository.save(seatEntity);
        } else {
            throw new Exception("Seat not found");
        }
        return true;
    }

    @Override
    public boolean removeSeat(RemoveSeatEntity removeSeatEntity) throws Exception {
        SeatEntity seatEntity=seatRepository.findByFlightIDAndSeatNumber(removeSeatEntity.getFlightID(),removeSeatEntity.getSeatNumber());
        if(seatEntity==null){
            throw new Exception("Seat not found");
        }
        if(!seatEntity.getAvailability()){
            throw new Exception("Seat is not available to remove");
        }
        seatRepository.deleteByFlightIDAndSeatNumber(removeSeatEntity.getFlightID(), removeSeatEntity.getSeatNumber());
        return true;
    }
}
