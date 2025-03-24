package com.maria.microservicio_hotel.repository;

import com.maria.microservicio_hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query("SELECT h FROM Hotel h WHERE disponible = true")
    List<Hotel> obtenerHotelesDisponibles();
}