package com.project.HotelServer.ServicesAuth.Admin.Rooms;

import com.project.HotelServer.Dto.RoomDto;
import com.project.HotelServer.Dto.RoomResponseDto;

public interface RoomsService {
    boolean postRoom(RoomDto roomDto);
    RoomResponseDto getAllRooms(int pageNumber);
    RoomDto getRoomByID(Long id);
    boolean UpdateRoom(Long id, RoomDto roomDto);
    void DeleteRoom(Long id);
}
