package com.utkuakgungor.controllers;

import com.utkuakgungor.entity.*;
import com.utkuakgungor.service.impl.FlightImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@RequestMapping(path = "/flight", produces = MediaType.APPLICATION_JSON_VALUE)
public class FlightControllers {

    private final FlightImpl flightImpl;

    @Autowired
    public FlightControllers(FlightImpl flightImpl) {
        this.flightImpl = flightImpl;
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addFlight(@RequestBody AddFlightEntity addFlightEntity) {
        try {
            return new ResponseEntity<>(flightImpl.addFlight(addFlightEntity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateFlight(@RequestBody UpdateFlightEntity updateFlightEntity) {
        try {
            return new ResponseEntity<>(flightImpl.updateFlight(updateFlightEntity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/remove", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> removeFlight(long id) {
        try {
            return new ResponseEntity<>(flightImpl.removeFlight(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/retrieve", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> retrieveFlightInfo(long flightID) {
        try {
            return new ResponseEntity<>(flightImpl.retrieveFlightInfo(flightID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
