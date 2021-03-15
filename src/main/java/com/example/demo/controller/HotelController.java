package com.example.demo.controller;

import com.example.demo.entity.Hotel;
import com.example.demo.payload.Result;
import com.example.demo.repository.HotelRepository;
import com.example.demo.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/hotel")
public class HotelController {
    final HotelRepository hotelRepository;
    final HotelService hotelService;

    public HotelController(HotelRepository hotelRepository, HotelService hotelService) {
        this.hotelRepository = hotelRepository;
        this.hotelService = hotelService;
    }
    //CREATE
    @PostMapping
    public Result add(@RequestBody Hotel hotel){
   return hotelService.addHotel(hotel);
    }


    //READ ALL HOTELS
    @GetMapping
    public List<Hotel> get(){
    return hotelRepository.findAll();
    }
    //

    //UPDATE
    @PutMapping("/{id}")
    public Result edit(@RequestBody Hotel hotel,@PathVariable Integer id){
return hotelService.editHotel(id, hotel);
    }
    //DELETE
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
return hotelService.deleteHotel(id);
    }
    //READ BY ID
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable Integer id){
return hotelService.getHotelById(id);
    }
}
