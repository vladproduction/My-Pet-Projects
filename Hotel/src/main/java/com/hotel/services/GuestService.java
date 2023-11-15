package com.hotel.services;

import com.hotel.models.guest.DocumentType;
import com.hotel.models.guest.Guest;
import com.hotel.models.guest.PaymentType;
import com.hotel.repositories.GuestRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class GuestService {

    @Autowired
    private final GuestRepository guestRepository;


    public Guest create(Guest guest){
        return guestRepository.save(guest);
    }

    public List<Guest> findAllGuests() {

        return (List<Guest>) guestRepository.findAll();
    }

    public Guest findGuestById(int id){
        return guestRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Guest updateGuestById(int id, Guest guest) {
        Guest currentGuest = findGuestById(id);
        currentGuest.setFirstName(guest.getFirstName());
        currentGuest.setSecondName(guest.getSecondName());
        currentGuest.setDocumentType(guest.getDocumentType());
        currentGuest.setDocumentData(guest.getDocumentData());

        currentGuest.setPaymentType(guest.getPaymentType());
        return guestRepository.save(currentGuest);

    }


    public void deleteGuestById(int id) {
        guestRepository.deleteById(id);
    }

    public List<Guest> departureToday() {
        Instant today = Instant.now();
        return guestRepository.departureToday(today);
    }

    public void deleteAll() {
        guestRepository.deleteAll();
    }
}
