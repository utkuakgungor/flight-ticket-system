package com.utkuakgungor.controllers;

import com.utkuakgungor.entity.PayEntity;
import com.utkuakgungor.service.impl.PaymentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@RequestMapping(path = "/payment",produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentControllers {

    private final PaymentImpl paymentImpl;

    @Autowired
    public PaymentControllers(PaymentImpl paymentImpl){
        this.paymentImpl=paymentImpl;
    }

    @PostMapping(path = "/pay",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> pay(@RequestBody PayEntity payEntity) {
        try {
            return new ResponseEntity<>(paymentImpl.pay(payEntity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
