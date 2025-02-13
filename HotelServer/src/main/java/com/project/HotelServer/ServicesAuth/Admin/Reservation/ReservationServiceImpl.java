package com.project.HotelServer.ServicesAuth.Admin.Reservation;

import com.project.HotelServer.Dto.ReservationResponseDto;
import com.project.HotelServer.Entity.Reservation;
import com.project.HotelServer.Repository.ReservationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public static  final int SEARCH_RESULT_PER_PAGE=4;

    public ReservationResponseDto getAllReservations(int pageNumber){
        Pageable pageable= PageRequest.of(pageNumber,SEARCH_RESULT_PER_PAGE);

        Page<Reservation> reservationPage=reservationRepository.findAll(pageable);

        ReservationResponseDto reservationResponseDto=new ReservationResponseDto();

        reservationResponseDto.setReservationDtoList(reservationPage.stream().map(Reservation::getReservationDto).collect(Collectors.toList()));

        reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());

        reservationResponseDto.setTotalPages(reservationPage.getTotalPages());

        return reservationResponseDto;
    }
}
