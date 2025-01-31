package com.project.HotelServer.ServicesAuth.Admin.Rooms;

import com.project.HotelServer.Dto.RoomDto;
import com.project.HotelServer.Dto.RoomResponseDto;
import com.project.HotelServer.Entity.Room;
import com.project.HotelServer.Repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RoomsServiceImpl implements RoomsService{
    private final RoomRepository roomRepository;

    public RoomsServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public boolean postRoom(RoomDto roomDto){
        try{
            Room room=new Room();
            room.setName(roomDto.getName());
            room.setPrice(roomDto.getPrice());
            room.setType(roomDto.getType());
            room.setAvailable(true);
            roomRepository.save(room);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public RoomResponseDto getAllRooms(int pageNumber){
        Pageable pageable= PageRequest.of(pageNumber,1);
        Page<Room> roomPage= roomRepository.findAll(pageable);

        RoomResponseDto roomResponseDto=new RoomResponseDto();
        roomResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
        roomResponseDto.setTotalpages(roomPage.getTotalPages());
        roomResponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));
        return roomResponseDto;
    }

}
