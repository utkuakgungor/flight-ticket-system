package com.utkuakgungor.service;

import com.utkuakgungor.entity.*;

public interface SeatInterface {

    boolean addSeat(AddSeatEntity addSeatEntity) throws Exception;
    boolean updateSeat(UpdateSeatEntity updateSeatEntity) throws Exception;
    boolean removeSeat(RemoveSeatEntity removeSeatEntity) throws Exception;
}
