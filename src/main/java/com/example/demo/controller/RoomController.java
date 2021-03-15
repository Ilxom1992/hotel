package com.example.demo.controller;

import com.example.demo.entity.Hotel;
import com.example.demo.entity.Room;
import com.example.demo.payload.Result;
import com.example.demo.payload.RoomDto;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.RoomService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/room")
public class RoomController {
   final RoomService roomService;
   final RoomRepository roomRepository;

    public RoomController(RoomService roomService, RoomRepository roomRepository) {
        this.roomService = roomService;

        this.roomRepository = roomRepository;
    }

    //CREATE
    @PostMapping
    public Result add(@RequestBody RoomDto room){
   return roomService.addRoom(room);
    }
    //READ ALL ROOMS
    @GetMapping
    public List<Room> get(){
    return roomRepository.findAll();
    }
    //READ ROOMS BY ID HOTEL
    @GetMapping(value = "/hotelRooms/{hotelId}")
    public Result getRoomsByIdHotel(@RequestParam int page,@PathVariable Integer hotelId){
        return roomService.getHotelByIdRooms(hotelId,page);
    }
    //UPDATE
    @PutMapping("/{id}")
    public Result edit(@RequestBody RoomDto roomDto,@PathVariable Integer id){
return roomService.editRoom(id,roomDto);
    }
    //DELETE
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
return roomService.deleteRoom(id);
    }
    //READ BY ID
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable Integer id){
return roomService.getRoomById(id);
    }
}
