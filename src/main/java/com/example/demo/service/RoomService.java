package com.example.demo.service;

import com.example.demo.entity.Hotel;
import com.example.demo.entity.Room;
import com.example.demo.payload.Result;
import com.example.demo.payload.RoomDto;
import com.example.demo.repository.HotelRepository;
import com.example.demo.repository.RoomRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {
    final HotelRepository hotelRepository;
    final RoomRepository roomRepository;

    public RoomService(HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

//  CREATE
    public Result addRoom(RoomDto roomDto){
       Room room=new Room();
       room.setFloor(roomDto.getFloor());
       room.setNumber(roomDto.getNumber());
       room.setSize(roomDto.getSize());
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (optionalHotel.isPresent()){
            room.setHotel(optionalHotel.get());
            roomRepository.save(room);
            return new Result("Room Bazaga Saqlandi",true);
        }
        return new Result("Hotel Bazadan topilmadi",false);
    }
    //UPDATE
    public Result editRoom(Integer id, RoomDto roomDto){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()){
            return new Result("Bunday Room Bazada Mavjud Emas",false);
        }
        Room room= optionalRoom.get();
        room.setFloor(roomDto.getFloor());
        room.setNumber(roomDto.getNumber());
        room.setSize(roomDto.getSize());
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (optionalHotel.isPresent()){
            room.setHotel(optionalHotel.get());
            roomRepository.save(room);
            return new Result("Room o'zgartirildi",true);
        }
        return new Result("Hotel Bazadan topilmadi",false);
    }
    //DELETE
    public Result deleteRoom(Integer id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()){
            return new Result("Bunday Id li Room Mavjud emas",false);
        }
        hotelRepository.deleteById(id);
        return new Result("Room O'chirildi",true);
    }
    //GET BY ID
    public Result getRoomById(Integer id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()) {
            return new Result("Bunday Id li Room Mavjud emas", false);
        }else
        return new Result("Siz ",true,optionalRoom.get());
    }
    public Result getHotelByIdRooms(int page,Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()){
            return new Result("Bunday Id li Hotel Mavjud emas",false);
        }
        Pageable pageable= PageRequest.of(page,10);
        return new Result("Siz Kiritgan Hotel Roomlari ",true,roomRepository.findAllByHotelId(id,pageable));
    }

    
}
