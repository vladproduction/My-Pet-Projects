package com.hotel.controllers;

import com.hotel.models.invoice.Invoice;
import com.hotel.services.InvoiceService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStream;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{bookingId}")
    public void createInvoice(@PathVariable int bookingId, HttpServletResponse response){
        Invoice invoice = invoiceService.createInvoice(bookingId);
        XSSFWorkbook xssfWorkbook = invoiceService.generate(invoice);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=Invioice.xlsx");
        try(OutputStream out = response.getOutputStream()){
            xssfWorkbook.write(out);
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

}
