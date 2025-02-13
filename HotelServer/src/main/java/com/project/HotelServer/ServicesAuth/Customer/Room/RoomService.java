package com.project.HotelServer.ServicesAuth.Customer.Room;

import com.project.HotelServer.Dto.RoomResponseDto;

public interface RoomService {
    RoomResponseDto getAllAvailableRooms(int pageNumber);
}
