package com.hotel.services;

import com.hotel.models.guest.DocumentType;
import com.hotel.models.guest.Guest;
import com.hotel.models.guest.PaymentType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.hotel")
public class GuestServiceTest {


    @Autowired
    private GuestService guestService;

    @Test
    public void crudTest(){
        guestService.deleteAll();
        Guest guest = new Guest(0, DocumentType.PASSPORT, PaymentType.CASH,"AA-123","John", "Dow");
        Guest saveGuest = guestService.create(guest);
        Assert.assertNotNull(saveGuest);
        Assert.assertNotSame(0,saveGuest.getId());
        Assert.assertEquals(guest.getDocumentType(),saveGuest.getDocumentType());
        Assert.assertEquals(guest.getPaymentType(),saveGuest.getPaymentType());
        Assert.assertEquals(guest.getDocumentData(),saveGuest.getDocumentData());
        Assert.assertEquals(guest.getFirstName(),saveGuest.getFirstName());
        Assert.assertEquals(guest.getSecondName(),saveGuest.getSecondName());

        Guest guestById = guestService.findGuestById(saveGuest.getId());
        Assert.assertNotNull(guestById);

        List<Guest> guests = guestService.findAllGuests();
        Assert.assertTrue(guests.size() == 1);

        saveGuest.setDocumentType(DocumentType.DRIVE_LICENSE);
        Guest updatedGuest = guestService.updateGuestById(saveGuest.getId(), saveGuest);
        Assert.assertNotNull(saveGuest);
        Assert.assertNotSame(0,updatedGuest.getId());
        Assert.assertEquals(DocumentType.DRIVE_LICENSE,updatedGuest.getDocumentType());
        Assert.assertEquals(guest.getPaymentType(),updatedGuest.getPaymentType());
        Assert.assertEquals(guest.getDocumentData(),updatedGuest.getDocumentData());
        Assert.assertEquals(guest.getFirstName(),updatedGuest.getFirstName());
        Assert.assertEquals(guest.getSecondName(),updatedGuest.getSecondName());

        guestService.deleteGuestById(updatedGuest.getId());

        List<Guest> guestsAfterRemove = guestService.findAllGuests();
        Assert.assertTrue(guestsAfterRemove.size() == 0);
    }


}
