package com.hotel.services;

import com.hotel.models.booking.Booking;
import com.hotel.models.guest.Guest;
import com.hotel.models.room.Room;
import com.hotel.models.room.RoomType;
import com.hotel.repositories.BookingRepository;
import com.hotel.repositories.GuestRepository;
import com.hotel.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingService {

    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingRepository bookingRepository;



    public Booking bookRoom(int roomId, int guestId, Instant arrivalDate, Instant departureDate){
        Guest guest = guestRepository.findById(guestId).orElseThrow();
        Room room = roomRepository.findById(roomId).orElseThrow();
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setGuest(guest);
        booking.setDateArrival(arrivalDate);
        booking.setDateDeparture(departureDate);
        return bookingRepository.save(booking);

    }

    //suspended
//    public Booking bookRoom(int roomId, int guestId, Instant arrivalDate, Instant departureDate){
//        Guest guest = guestRepository.findById(guestId).orElseThrow();
//        Room room = roomRepository.findById(roomId).orElseThrow();
//        Booking booking = new Booking();
//        booking.setRoom(room);
//        booking.setGuest(guest);
//        booking.setDateArrival(arrivalDate);
//        booking.setDateDeparture(departureDate);
//        return bookingRepository.save(booking);
//    }




    public Guest bookRoomByCapacity(int capacity, int guestId, Instant arrivalDate, Instant departureDate){
        Guest guest = guestRepository.findById(guestId).orElseThrow();
        List<Room> allByCapacity = roomRepository.findAllByCapacity(capacity);
        for (Room room : allByCapacity) {
               // guest.setRoom(room);
                break;
        }
        return guestRepository.save(guest);
    }

    public Guest bookRoomByType(RoomType roomType, int guestId){
        Guest guest = guestRepository.findById(guestId).orElseThrow();
        List<Room> allByRoomType = roomRepository.findAllByRoomType(roomType);
        for (Room room : allByRoomType) {
              //  guest.setRoom(room);
                break;
        }
        return guestRepository.save(guest);
    }

    public List<Room> findAllBookedRoomsByDate(Instant arrivalDate, Instant departureDate){
        return bookingRepository.findAllBookedRoomsByDate(arrivalDate, departureDate);
    }

    public List<Room> findAllFreeRoomsByDate(Instant arrivalDate,Instant departureDate){
        return bookingRepository.findAllFreeRoomsByDate(arrivalDate, departureDate);
    }

    public List<Room> findAllFreeRoomsByDateAndCapacity(Instant arrivalDate,
                                                        Instant departureDate,
                                                        int capacity){
        return bookingRepository.findAllFreeRoomsByDateAndCapacity(arrivalDate, departureDate, capacity);
    }

    public List<Room> findAllFreeRoomsByDateAndRoomType(Instant arrivalDate,
                                                        Instant departureDate,
                                                        RoomType roomType){
        return bookingRepository.findAllFreeRoomsByDateAndRoomType(arrivalDate, departureDate, roomType);
    }

    public List<Room> findAllFreeRoomsByDateAndRoomTypeAndCapacity(Instant arrivalDate,
                                                                   Instant departureDate,
                                                                   RoomType roomType,
                                                                   int capacity){
        return bookingRepository
                .findAllFreeRoomsByDateAndRoomTypeAndCapacity(arrivalDate, departureDate, roomType, capacity);
    }


    public boolean prolongBooking(int bookingId, int prolongDays) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        Room room = booking.getRoom();
        Instant currentDepart = booking.getDateDeparture();
        Instant prolongArriveDate = currentDepart.plus(1, ChronoUnit.DAYS);
        Instant prolongedDays = currentDepart.plus(prolongDays, ChronoUnit.DAYS);
        Room roomBooked = bookingRepository.isRoomBooked(prolongArriveDate, prolongedDays, room.getId());
        if(roomBooked==null){
            booking.setDateDeparture(prolongedDays);
            bookingRepository.save(booking);
            return true;
        }
        return false;
    }

    public boolean earlyDeparture(int bookingId, int days) {
        if(days<=0){
            return  false;
        }
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        Instant currentDepart = booking.getDateDeparture();
        Instant earlyDeparture = currentDepart.minus(days, ChronoUnit.DAYS);
        Instant today = Instant.now();
        long between = ChronoUnit.DAYS.between(today, earlyDeparture);
        if(between > 0){
            booking.setDateDeparture(earlyDeparture);
            bookingRepository.save(booking);
            return true;
        }
        return  false;
    }

    public boolean departure(int bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        Instant today = Instant.now();
        long between = ChronoUnit.DAYS.between(today, booking.getDateDeparture());
        if(between==0){
            bookingRepository.deleteById(bookingId);
            return true;
        }
        return  false;
    }
}
