package com.utkuakgungor.service;


import com.utkuakgungor.entity.PayEntity;

public interface PaymentInterface {

    boolean pay(PayEntity payEntity) throws Exception;
}
