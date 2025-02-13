package com.project.HotelServer.Controller.admin;

import com.project.HotelServer.Dto.RoomDto;
import com.project.HotelServer.ServicesAuth.Admin.Rooms.RoomsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class RoomsController {
    private final RoomsService roomsService;
    public RoomsController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    @PostMapping("/room")
    public ResponseEntity<?> postRoom(@RequestBody RoomDto roomDto) {
        boolean success = roomsService.postRoom(roomDto);

        if (success) {
            return ResponseEntity.ok().body(Map.of(
                    "status", "success",
                    "message", "Room created successfully"
            ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "error",
                    "message", "Failed to create room"
            ));
        }
    }

    @GetMapping("/rooms/{pageNumber}")
    public ResponseEntity<?> getAllRooms(@PathVariable int pageNumber){
        return ResponseEntity.ok(roomsService.getAllRooms(pageNumber));
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(roomsService.getRoomByID(id));
        }catch (EntityNotFoundException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Somthing went wrong");
        }
    }



    @PutMapping("/room/{id}")
    public ResponseEntity<?> UpdateRoom(@PathVariable Long id,@RequestBody RoomDto roomDto){
        boolean success=roomsService.UpdateRoom(id,roomDto);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/room/{id}")
    public ResponseEntity<?> DeleteRoom(@PathVariable Long id){
        try{
            roomsService.DeleteRoom(id);
            return ResponseEntity.ok(null);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
