package com.project.HotelServer.ServicesAuth.Customer.Booking;

import com.project.HotelServer.Dto.ReservationDto;
import com.project.HotelServer.Entity.Reservation;
import com.project.HotelServer.Entity.Room;
import com.project.HotelServer.Entity.User;
import com.project.HotelServer.Enums.ReservationStatus;
import com.project.HotelServer.Repository.ReservationRepository;
import com.project.HotelServer.Repository.RoomRepository;
import com.project.HotelServer.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class BookingServiceImpl {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public BookingServiceImpl(UserRepository userRepository, RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    public boolean postReservation(ReservationDto reservationDto){
        Optional<User> optionalUser=userRepository.findById(reservationDto.getUserId());
        Optional<Room> optionalRoom=roomRepository.findById(reservationDto.getRoomId());

        if(optionalRoom.isPresent() && optionalUser.isPresent()){
            Reservation reservation=new Reservation();
            reservation.setRoom(optionalRoom.get());
            reservation.setUser(optionalUser.get());
            reservation.setCheckInDate(reservationDto.getCheckInDate());
            reservation.setCheckOutDate(reservationDto.getCheckOutDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);

            Long days= ChronoUnit.DAYS.between(reservationDto.getCheckInDate(),reservationDto.getCheckOutDate());
            reservation.setPrice(optionalRoom.get().getPrice()+days);
            reservationRepository.save(reservation);
            return true;
        }

        return false;



    }

}
