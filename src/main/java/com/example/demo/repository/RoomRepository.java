package com.example.demo.repository;

import com.example.demo.entity.Hotel;
import com.example.demo.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Integer> {
Page<Room> findAllByHotelId(Integer hotel_id, Pageable pageable);
}
