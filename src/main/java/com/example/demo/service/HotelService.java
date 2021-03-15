package com.example.demo.service;

import com.example.demo.entity.Hotel;
import com.example.demo.payload.Result;
import com.example.demo.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelService {
    final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

//  CREATE
    public Result addHotel(Hotel hotel){
        boolean existsByName = hotelRepository.existsByName(hotel.getName());
        if (existsByName){
            return new Result("Bunday Hotel Bazada Mavjud",false);
        }
        hotelRepository.save(hotel);
        return new Result("Hotel Bazaga Saqlandi",true);
    }
    //UPDATE
    public Result editHotel(Integer id, Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()){
            return new Result("Bunday Hotel Bazada Mavjud Emas",false);
        }
        Hotel hotel1= optionalHotel.get();
        hotel1.setName(hotel.getName());
        hotelRepository.save(hotel1);
        return new Result("Hotel Nomi O'zgartirildi",true);
    }
    //DELETE
    public Result deleteHotel(Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()){
            return new Result("Bunday Id li Hotel Mavjud emas",false);
        }
        hotelRepository.deleteById(id);
        return new Result("Hotel O'chirildi",true);
    }
    //GET BY ID
    public Result getHotelById(Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()) {
            return new Result("Bunday Id li Hotel Mavjud emas", false);
        }else
        return new Result("Hotel O'chirildi",true,optionalHotel.get());
    }

}
