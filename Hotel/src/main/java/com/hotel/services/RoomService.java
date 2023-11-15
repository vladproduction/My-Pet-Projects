package com.hotel.services;

import com.hotel.models.room.Room;
import com.hotel.models.room.RoomType;
import com.hotel.repositories.RoomRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    

    public Room create(Room room){
        return roomRepository.save(room);
    }

    public List<Room> findAllRooms(){
        return (List<Room>) roomRepository.findAll();
    }

    public Room findById(int id){

        return roomRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Room updateById(int id, Room room){
        Room currentRoom = findById(id);
        currentRoom.setRoomNumber(room.getRoomNumber());
        currentRoom.setRoomType(room.getRoomType());
        currentRoom.setCapacity(room.getCapacity());
        currentRoom.setPrice(room.getPrice());
        return roomRepository.save(currentRoom);
    }


    public void deleteById(int id) {
        roomRepository.deleteById(id);
    }

    public List<Room> findAllByRoomType(RoomType roomType){
        return roomRepository.findAllByRoomType(roomType);
    }

    public List<Room> findAllRoomByCapacity(int capacity){
        return roomRepository.findAllByCapacity(capacity);
    }

    public List<Room> findAllRoomByCapacityAndRoomType(int capacity, RoomType roomType){
        return roomRepository.findAllRoomByCapacityAndRoomType(capacity, roomType);
    }


    public void deleteAll() {
        roomRepository.deleteAll();
    }
}
