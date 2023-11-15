package com.hotel.controllers;

import com.hotel.models.room.Room;
import com.hotel.models.room.RoomType;
import com.hotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public Room create(@RequestBody Room room){
        return roomService.create(room);
    }

    @GetMapping
    public List<Room> findAllRooms(){
        return roomService.findAllRooms();
    }

    @GetMapping("/{id}")
    public Room findById(@PathVariable int id){
        return roomService.findById(id);
    }

    @PutMapping("/{id}")
    public Room updateById(@PathVariable int id, @RequestBody Room room){
        return roomService.updateById(id, room);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        roomService.deleteById(id);
    }

    @GetMapping("/roomtype/{roomType}")
    public List<Room> findAllRoomByType(@PathVariable RoomType roomType){
        return roomService.findAllByRoomType(roomType);
    }

    @GetMapping("/capacity/{capacity}")
    public List<Room> findAllRoomByCapacity(@PathVariable int capacity){
        return roomService.findAllRoomByCapacity(capacity);
    }

    @GetMapping("/capacity/{capacity}/roomtype/{roomType}")
    public List<Room> findAllRoomByCapacityAndRoomType(@PathVariable int capacity, @PathVariable RoomType roomType){
        return roomService.findAllRoomByCapacityAndRoomType(capacity, roomType);
    }

    @GetMapping("/free")
    public List<Room> findFreeRooms(@RequestParam Instant arrivalDate, @RequestParam Instant departureDate){
        return null;
    }
    @DeleteMapping
    public void deleteAllRooms(){
        roomService.deleteAll();
    }
}
