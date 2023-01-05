package com.utkuakgungor.repository;

import com.utkuakgungor.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;

public interface SeatRepository extends JpaRepository<SeatEntity, Long> {
    List<SeatEntity> findByFlightIDAndAvailabilityTrue(@NonNull long flightID);
    SeatEntity findByFlightIDAndSeatNumber(@NonNull long flightID, @NonNull int seatNumber);
    void deleteByFlightIDAndSeatNumber(@NonNull long flightID, @NonNull int seatNumber);
    @Query(value = "select s.seatNumber from SeatEntity s where s.flightID = ?1 order by s.seatNumber desc FETCH FIRST 1 ROWS ONLY",nativeQuery = true)
    int findBiggestSeatNumber(@NonNull long flightID);
}
