package com.hotel.models.room;

import com.hotel.models.guest.Guest;
import jakarta.persistence.*;
import lombok.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private int capacity;
    private int roomNumber;
    private int price;



}
