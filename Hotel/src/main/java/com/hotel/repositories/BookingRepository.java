package com.hotel.repositories;

import com.hotel.models.booking.Booking;
import com.hotel.models.room.Room;
import com.hotel.models.room.RoomType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends CrudRepository<Booking, Integer> {

    @Query("select b.room from Booking b where " +
            "(b.dateArrival <= :arrivalDate and :arrivalDate<=b.dateDeparture) or " +
            "(b.dateArrival <= :departureDate and :departureDate<=b.dateDeparture)")

    public List<Room> findAllBookedRoomsByDate(@Param("arrivalDate") Instant arrivalDate,
                                               @Param("departureDate") Instant departuredate);

    @Query("select booking.room from Booking booking " +
            "where booking.room not in (select b.room from Booking b where " +
            "(b.dateArrival <= :arrivalDate and :arrivalDate<=b.dateDeparture) or " +
            "(b.dateArrival <= :departureDate and :departureDate<=b.dateDeparture))")

    public List<Room> findAllFreeRoomsByDate(@Param("arrivalDate") Instant arrivalDate,
                                               @Param("departureDate") Instant departureDate);

    @Query("select booking.room from Booking booking " +
            "where booking.room.capacity = :capacity and booking.room not in (select b.room from Booking b where " +
            "(b.dateArrival <= :arrivalDate and :arrivalDate<=b.dateDeparture) or " +
            "(b.dateArrival <= :departureDate and :departureDate<=b.dateDeparture))")

    public List<Room> findAllFreeRoomsByDateAndCapacity(@Param("arrivalDate") Instant arrivalDate,
                                             @Param("departureDate") Instant departureDate,
                                                        @Param("capacity") int capacity);

    @Query("select booking.room from Booking booking " +
            "where booking.room.roomType = :roomType and booking.room not in (select b.room from Booking b where " +
            "(b.dateArrival <= :arrivalDate and :arrivalDate<=b.dateDeparture) or " +
            "(b.dateArrival <= :departureDate and :departureDate<=b.dateDeparture))")

    public List<Room> findAllFreeRoomsByDateAndRoomType(@Param("arrivalDate") Instant arrivalDate,
                                                        @Param("departureDate") Instant departureDate,
                                                        @Param("roomType") RoomType roomType);

    @Query("select booking.room from Booking booking " +
            "where booking.room.roomType = :roomType and booking.room.capacity = :capacity " +
            "and booking.room not in (select b.room from Booking b where " +
            "(b.dateArrival <= :arrivalDate and :arrivalDate<=b.dateDeparture) or " +
            "(b.dateArrival <= :departureDate and :departureDate<=b.dateDeparture))")

    public List<Room> findAllFreeRoomsByDateAndRoomTypeAndCapacity(@Param("arrivalDate") Instant arrivalDate,
                                                                   @Param("departureDate") Instant departureDate,
                                                                   @Param("roomType") RoomType roomType,
                                                                   @Param("capacity") int capacity);



    @Query("select b.room from Booking b where b.room.id = :roomId and" +
            "(b.dateArrival <= :arrivalDate and :arrivalDate<=b.dateDeparture) or " +
            "(b.dateArrival <= :departureDate and :departureDate<=b.dateDeparture)")
    public Room isRoomBooked(@Param("arrivalDate") Instant arrivalDate,
                                @Param("departureDate") Instant departureDate,
                                @Param("roomId") int roomId);

}
