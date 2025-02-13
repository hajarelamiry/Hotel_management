package com.project.HotelServer.ServicesAuth.Customer.Room;

import com.project.HotelServer.Dto.RoomResponseDto;
import com.project.HotelServer.Entity.Room;
import com.project.HotelServer.Repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService{

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    private final RoomRepository roomRepository;


    public RoomResponseDto getAllAvailableRooms(int pageNumber){
        Pageable pageable= PageRequest.of(pageNumber,6);
        Page<Room> roomPage= roomRepository.findByAvailable(true,pageable);

        RoomResponseDto roomResponseDto=new RoomResponseDto();
        roomResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
        roomResponseDto.setTotalpages(roomPage.getTotalPages());
        roomResponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));
        return roomResponseDto;
    }

}
