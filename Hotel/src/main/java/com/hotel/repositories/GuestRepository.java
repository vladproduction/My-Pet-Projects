package com.hotel.repositories;

import com.hotel.models.guest.Guest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface GuestRepository extends CrudRepository<Guest, Integer> {

    @Query("select b.guest from Booking b where b.dateDeparture= :today")
    List<Guest> departureToday(@Param("today") Instant today);
}
