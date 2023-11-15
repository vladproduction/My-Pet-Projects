package com.hotel.controllers;

import com.hotel.models.booking.Booking;
import com.hotel.models.guest.Guest;
import com.hotel.models.room.Room;
import com.hotel.models.room.RoomType;
import com.hotel.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;



    @PostMapping("/room/{roomId}/guest/{guestId}")
    public Booking bookRoom(@PathVariable int roomId, @PathVariable int guestId,
                            @RequestParam Instant arrivalDate, @RequestParam Instant departureDate){
        return bookingService.bookRoom(roomId, guestId, arrivalDate, departureDate);
    }

    @PostMapping("/roomcapacity/{capacity}/guest/{guestId}")
    public Guest bookRoomByCapacity(@PathVariable int capacity, @PathVariable int guestId,
    @RequestParam Instant arrivalDate, @RequestParam Instant departureDate){
        return bookingService.bookRoomByCapacity(capacity, guestId, arrivalDate, departureDate);
    }

    @PostMapping("/roomtype/{roomtype}/guest/{guestId}")
    public Guest bookRoomByType(@PathVariable RoomType roomtype, @PathVariable int guestId){
        return bookingService.bookRoomByType(roomtype, guestId);
    }

    @GetMapping("/booked")
    public List<Room> findAllBookedRoomsByDate(@RequestParam Instant arrivalDate, @RequestParam Instant departureDate){
        return bookingService.findAllBookedRoomsByDate(arrivalDate, departureDate);
    }

    @GetMapping("/free")
    public List<Room> findAllFreeRoomsByDate(@RequestParam Instant arrivalDate, @RequestParam Instant departureDate){
        return bookingService.findAllFreeRoomsByDate(arrivalDate, departureDate);
    }

    @GetMapping("/free/capacity/{capacity}")
    public List<Room> findAllFreeRoomsByDateAndCapacity(@RequestParam Instant arrivalDate,
                                                        @RequestParam Instant departureDate,
                                                        @PathVariable int capacity){
        return bookingService.findAllFreeRoomsByDateAndCapacity(arrivalDate, departureDate, capacity);
    }

    @GetMapping("/free/roomtype/{roomType}")
    public List<Room> findAllFreeRoomsByDateAndRoomType(@RequestParam Instant arrivalDate,
                                                        @RequestParam Instant departureDate,
                                                        @PathVariable RoomType roomType){
        return bookingService.findAllFreeRoomsByDateAndRoomType(arrivalDate, departureDate, roomType);
    }

    @GetMapping("/free/roomtype/{roomType}/capacity/{capacity}")
    public List<Room> findAllFreeRoomsByDateAndRoomTypeAndCapacity(@RequestParam Instant arrivalDate,
                                                                   @RequestParam Instant departureDate,
                                                                   @PathVariable RoomType roomType,
                                                                   @PathVariable int capacity){
        return bookingService
                .findAllFreeRoomsByDateAndRoomTypeAndCapacity(arrivalDate, departureDate, roomType, capacity);
    }


    @PutMapping("/prolong/{bookingId}")
    public boolean prolongBooking(@PathVariable int bookingId, @RequestParam int prolongDays){
        return bookingService.prolongBooking(bookingId, prolongDays);
    }

    @PutMapping("/earlydeparture/{bookingId}")
    public boolean earlyDeparture(@PathVariable int bookingId, @RequestParam int days){
        return  bookingService.earlyDeparture(bookingId, days);

    }

    @DeleteMapping("/departure/{bookingId}")
    public boolean departure(@PathVariable int bookingId){
        return bookingService.departure(bookingId);
    }

}
