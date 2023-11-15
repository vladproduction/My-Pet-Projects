package com.hotel.controllers;

import com.hotel.models.guest.Guest;
import com.hotel.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @PostMapping
    public Guest create(@RequestBody Guest guest){
        return guestService.create(guest);
    }

    @GetMapping
    public List<Guest> findAllGuests(){
        return guestService.findAllGuests();
    }

    @GetMapping("/{id}")
    public Guest findById(@PathVariable int id){
        return guestService.findGuestById(id);
    }

    @PutMapping("/{id}")
    public Guest updateGuestById(@PathVariable int id, @RequestBody Guest guest){
        return guestService.updateGuestById(id, guest);
    }

    @DeleteMapping("/{id}")
    public void deleteGuestById(@PathVariable int id){
        guestService.deleteGuestById(id);
    }

    @GetMapping("/departure")
    public List<Guest> departureToday(){
        return guestService.departureToday();
    }

    @DeleteMapping
    public void deleteAllRooms(){
        guestService.deleteAll();
    }


}
