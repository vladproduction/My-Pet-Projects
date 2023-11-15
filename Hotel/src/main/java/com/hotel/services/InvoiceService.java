package com.hotel.services;

import com.hotel.models.booking.Booking;
import com.hotel.models.guest.Guest;
import com.hotel.models.invoice.Invoice;
import com.hotel.models.room.Room;
import com.hotel.repositories.BookingRepository;
import com.hotel.repositories.GuestRepository;
import com.hotel.repositories.RoomRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class InvoiceService {

    @Autowired
    private BookingRepository bookingRepository;

    public Invoice createInvoice(int bookingId){
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        long daysRented = daysRented(booking);
        Room room = booking.getRoom();
        long totalPrice = daysRented * room.getPrice();
        return new Invoice(totalPrice, booking);
    }

    public XSSFWorkbook generate(Invoice invoice){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("REPORT ORDER");
        int rowNumber = 0;
        Row row = sheet.createRow(rowNumber++);
        Cell cellKey = row.createCell(0);
        Cell cellValue = row.createCell(1);
        cellKey.setCellValue("TOTAL PRICE:");
        cellValue.setCellValue(invoice.getTotalPrice());

        Guest guest = invoice.getBooking().getGuest();
        row = sheet.createRow(rowNumber++);
        cellKey = row.createCell(0);
        cellValue = row.createCell(1);
        cellKey.setCellValue("GUEST:");
        cellValue.setCellValue(guest.getFirstName() + " "+guest.getSecondName());

        Room room = invoice.getBooking().getRoom();
        row = sheet.createRow(rowNumber++);
        cellKey = row.createCell(0);
        cellValue = row.createCell(1);
        cellKey.setCellValue("ROOM:");
        cellValue.setCellValue(room.getCapacity() + " " +room.getRoomType() + " " +room.getPrice());

        Booking booking = invoice.getBooking();
        row = sheet.createRow(rowNumber++);
        cellKey = row.createCell(0);
        cellValue = row.createCell(1);
        cellKey.setCellValue("DATES:");
        cellValue.setCellValue(booking.getDateArrival() + " " +booking.getDateDeparture());
        return workbook;
    }

    private long daysRented(Booking booking){
        Instant dateArrival = booking.getDateArrival();
        Instant dateDeparture = booking.getDateDeparture();
        return ChronoUnit.DAYS.between(dateArrival, dateDeparture);
    }
}
