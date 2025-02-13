package com.project.HotelServer.Controller.Customer;


import com.project.HotelServer.ServicesAuth.Customer.Room.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/customer")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/room/{pageNumber}")
    public ResponseEntity<?> getAllAvailableRoom(@PathVariable int pageNumber){
        return ResponseEntity.ok(roomService.getAllAvailableRooms(pageNumber));
    }
}
