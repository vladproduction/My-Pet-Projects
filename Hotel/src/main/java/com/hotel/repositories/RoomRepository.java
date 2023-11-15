package com.hotel.repositories;

import com.hotel.models.room.Room;
import com.hotel.models.room.RoomType;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Integer> {

    public List<Room> findAllByRoomType(RoomType roomType);

    public List<Room> findAllByCapacity(int capacity);

    public List<Room> findAllRoomByCapacityAndRoomType(int capacity, RoomType roomType);



}
