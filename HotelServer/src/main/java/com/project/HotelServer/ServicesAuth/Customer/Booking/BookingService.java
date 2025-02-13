package com.project.HotelServer.ServicesAuth.Customer.Booking;

import com.project.HotelServer.Dto.ReservationDto;
import org.springframework.stereotype.Service;



public interface BookingService {
    boolean postReservation(ReservationDto reservationDto);
}
