package com.maria.microservicio_reserva.feign;

import com.maria.microservicio_reserva.entity.Hotel;
import com.maria.microservicio_reserva.util.Error;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "microservicio-hotel", path = "/api/hoteles")
public interface HotelFeign {
    @GetMapping("/{id}")
    ResponseEntity<Hotel> obtenerHotel(@PathVariable long id);

    @PostMapping("/{id}")
    ResponseEntity<Error> reservarHotel(@PathVariable long id);
}