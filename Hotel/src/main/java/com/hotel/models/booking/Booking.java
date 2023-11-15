package com.hotel.models.booking;

import com.hotel.models.guest.Guest;
import com.hotel.models.room.Room;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Room room;
    @ManyToOne
    private Guest guest;
    private Instant dateArrival;
    private Instant dateDeparture;

}
