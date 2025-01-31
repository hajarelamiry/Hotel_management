package com.project.HotelServer.ServicesAuth.Admin.Rooms;

import com.project.HotelServer.Dto.RoomDto;
import com.project.HotelServer.Dto.RoomResponseDto;

public interface RoomsService {
    boolean postRoom(RoomDto roomDto);
    RoomResponseDto getAllRooms(int pageNumber);
}
