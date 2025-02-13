package com.project.HotelServer.ServicesAuth.Admin.Reservation;

import com.project.HotelServer.Dto.ReservationResponseDto;

public interface ReservationService {
    ReservationResponseDto getAllReservations(int pageNumber);
}
