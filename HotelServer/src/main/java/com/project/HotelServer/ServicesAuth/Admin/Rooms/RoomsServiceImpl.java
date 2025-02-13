package com.project.HotelServer.ServicesAuth.Admin.Rooms;

import com.project.HotelServer.Dto.RoomDto;
import com.project.HotelServer.Dto.RoomResponseDto;
import com.project.HotelServer.Entity.Room;
import com.project.HotelServer.Repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Optional;
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
        Pageable pageable= PageRequest.of(pageNumber,6);
        Page<Room> roomPage= roomRepository.findAll(pageable);

        RoomResponseDto roomResponseDto=new RoomResponseDto();
        roomResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
        roomResponseDto.setTotalpages(roomPage.getTotalPages());
        roomResponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));
        return roomResponseDto;
    }

    public RoomDto getRoomByID(Long id){
        Optional<Room> optionalRoom=roomRepository.findById(id);
        if(optionalRoom.isPresent()){
            return optionalRoom.get().getRoomDto();
        }else{
            throw new EntityNotFoundException("Room not found");
        }
    }

    public boolean UpdateRoom(Long id, RoomDto roomDto){
        Optional<Room> optionalRoom=roomRepository.findById(id);
        if(optionalRoom.isPresent()){
            Room exictingRoom=optionalRoom.get();

            exictingRoom.setName(roomDto.getName());
            exictingRoom.setPrice(roomDto.getPrice());
            exictingRoom.setType(roomDto.getType());

            roomRepository.save(exictingRoom);
            return true;
        }else{
            return false;
        }
    }

    public void DeleteRoom(Long id){
        Optional<Room> optionalRoom=roomRepository.findById(id);
        if(optionalRoom.isPresent()){
            roomRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Room not Present");
        }
    }

}
