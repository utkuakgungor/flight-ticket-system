package com.utkuakgungor.repository;

import com.utkuakgungor.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface FlightRepository extends JpaRepository<FlightEntity, Long> {
    FlightEntity findByFlightID(@NonNull Long flightID);
    boolean existsByName(@NonNull String name);
    boolean existsByFlightID(@NonNull Long flightID);
    @Override
    void deleteById(Long id);

    @Override
    <S extends FlightEntity> S save(S entity);
}
