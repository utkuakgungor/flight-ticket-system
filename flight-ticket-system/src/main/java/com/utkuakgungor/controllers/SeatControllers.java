package com.utkuakgungor.controllers;

import com.utkuakgungor.entity.*;
import com.utkuakgungor.service.impl.SeatImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@RequestMapping(path = "/seat", produces = MediaType.APPLICATION_JSON_VALUE)
public class SeatControllers {

    private final SeatImpl seatImpl;

    @Autowired
    public SeatControllers(SeatImpl seatImpl) {
        this.seatImpl = seatImpl;
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addSeat(@RequestBody AddSeatEntity addSeatEntity) {
        try {
            return new ResponseEntity<>(seatImpl.addSeat(addSeatEntity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateSeat(@RequestBody UpdateSeatEntity updateSeatEntity) {
        try {
            return new ResponseEntity<>(seatImpl.updateSeat(updateSeatEntity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> removeSeat(@RequestBody RemoveSeatEntity removeSeatEntity) {
        try {
            return new ResponseEntity<>(seatImpl.removeSeat(removeSeatEntity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
