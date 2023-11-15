package com.hotel.repositories;

import com.hotel.models.guest.DocumentType;
import com.hotel.models.guest.Guest;
import com.hotel.models.guest.PaymentType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@RunWith(SpringRunner.class)
public class GuestRepositoryTest {

    @Autowired
    private GuestRepository guestRepository;

    @Test
    public void crudTest(){
        guestRepository.deleteAll();//clean
        Guest guest = new Guest(0, DocumentType.PASSPORT, PaymentType.CASH,"AA-123","John", "Dow");
        Guest saveGuest = guestRepository.save(guest);
        Assert.assertNotNull(saveGuest);
        Assert.assertNotSame(0,saveGuest.getId());
        Assert.assertEquals(guest.getDocumentType(),saveGuest.getDocumentType());
        Assert.assertEquals(guest.getPaymentType(),saveGuest.getPaymentType());
        Assert.assertEquals(guest.getDocumentData(),saveGuest.getDocumentData());
        Assert.assertEquals(guest.getFirstName(),saveGuest.getFirstName());
        Assert.assertEquals(guest.getSecondName(),saveGuest.getSecondName());

        Optional<Guest> guestById = guestRepository.findById(saveGuest.getId());
        Assert.assertTrue(guestById.isPresent());

        Optional<Guest> guestByIdNotExist = guestRepository.findById(-1);
        Assert.assertFalse(guestByIdNotExist.isPresent());

        List<Guest> guests = (List<Guest>) guestRepository.findAll();
        Assert.assertTrue(guests.size() == 1);

        saveGuest.setDocumentType(DocumentType.DRIVE_LICENSE);
        Guest updatedGuest = guestRepository.save(saveGuest);
        Assert.assertNotNull(saveGuest);
        Assert.assertNotSame(0,updatedGuest.getId());
        Assert.assertEquals(DocumentType.DRIVE_LICENSE,updatedGuest.getDocumentType());
        Assert.assertEquals(guest.getPaymentType(),updatedGuest.getPaymentType());
        Assert.assertEquals(guest.getDocumentData(),updatedGuest.getDocumentData());
        Assert.assertEquals(guest.getFirstName(),updatedGuest.getFirstName());
        Assert.assertEquals(guest.getSecondName(),updatedGuest.getSecondName());

        guestRepository.deleteById(updatedGuest.getId());

        List<Guest> guestsAfterRemove = (List<Guest>) guestRepository.findAll();
        Assert.assertTrue(guestsAfterRemove.size() == 0);
    }

}
