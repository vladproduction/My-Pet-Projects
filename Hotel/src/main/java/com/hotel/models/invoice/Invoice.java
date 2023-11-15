package com.hotel.models.invoice;

import com.hotel.models.booking.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    private long totalPrice;
    private Booking booking;
}
