package com.utkuakgungor.service;


import com.utkuakgungor.entity.*;

public interface FlightInterface {

    boolean addFlight(AddFlightEntity addFlightEntity) throws Exception;
    boolean updateFlight(UpdateFlightEntity updateFlightEntity) throws Exception;
    boolean removeFlight(long id) throws Exception;
    FlightInfoEntity retrieveFlightInfo(long flightID) throws Exception;
}
