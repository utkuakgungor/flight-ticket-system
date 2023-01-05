package com.utkuakgungor.service.impl;

import com.utkuakgungor.entity.*;
import com.utkuakgungor.repository.FlightRepository;
import com.utkuakgungor.repository.SeatRepository;
import com.utkuakgungor.service.FlightInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FlightImpl implements FlightInterface {

    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;

    public FlightImpl(FlightRepository flightRepository, SeatRepository seatRepository) {
        this.flightRepository = flightRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    public boolean addFlight(AddFlightEntity addFlightEntity) throws Exception {
        FlightEntity flightEntity = new FlightEntity();
        if (flightRepository.existsByFlightID(addFlightEntity.getFlightID())) {
            throw new Exception("Flight id exists");
        }
        if (flightRepository.existsByName(addFlightEntity.getFlightName())) {
            throw new Exception("Flight name exists");
        }
        flightEntity.setCapacity(addFlightEntity.getCapacity());
        flightEntity.setName(addFlightEntity.getFlightName());
        flightEntity.setDescription(addFlightEntity.getDescription());
        flightEntity.setFlightID(addFlightEntity.getFlightID());
        flightRepository.save(flightEntity);
        return true;
    }

    @Override
    public boolean updateFlight(UpdateFlightEntity updateFlightEntity) throws Exception {
        FlightEntity flightEntity = flightRepository.findByFlightID(updateFlightEntity.getFlightID());
        if (flightEntity != null) {
            if (seatRepository.findBiggestSeatNumber(updateFlightEntity.getFlightID()) > updateFlightEntity.getCapacity()) {
                throw new Exception("Capacity is lower than seats");
            }
            flightEntity.setCapacity(updateFlightEntity.getCapacity());
            flightEntity.setDescription(updateFlightEntity.getDescription());
            flightRepository.save(flightEntity);
        } else {
            throw new Exception("Flight not found");
        }
        return true;
    }

    @Override
    public boolean removeFlight(long id) throws Exception {
        FlightEntity flightEntity=flightRepository.findByFlightID(id);
        if(flightEntity==null){
            throw new Exception("Flight not found");
        }
        flightRepository.deleteById(id);
        return true;
    }

    @Override
    public FlightInfoEntity retrieveFlightInfo(long flightID) throws Exception {
        FlightInfoEntity flightInfoEntity = new FlightInfoEntity();
        FlightEntity flightEntity = flightRepository.findByFlightID(flightID);
        if (flightEntity == null) {
            throw new Exception("Flight not found");
        }
        flightInfoEntity.setFlightName(flightEntity.getName());
        flightInfoEntity.setDescription(flightEntity.getDescription());
        List<SeatEntity> seatEntityList = seatRepository.findByFlightIDAndAvailabilityTrue(flightID);
        List<SeatInfoEntity> seatInfoEntityList=new ArrayList<>();
        for(SeatEntity seatEntity:seatEntityList){
            SeatInfoEntity seatInfoEntity=new SeatInfoEntity();
            seatInfoEntity.setPrice(seatEntity.getPrice());
            seatInfoEntity.setSeatNumber(seatEntity.getSeatNumber());
            seatInfoEntityList.add(seatInfoEntity);
        }
        flightInfoEntity.setAvailableSeats(seatInfoEntityList);
        return flightInfoEntity;
    }
}
