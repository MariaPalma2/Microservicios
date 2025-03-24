package com.maria.microservicio_reserva.feign;

import com.maria.microservicio_reserva.entity.Vuelo;
import com.maria.microservicio_reserva.util.Error;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "microservicio-vuelo", path = "/api/vuelos")
public interface VueloFeign {
    @GetMapping("/{id}")
    ResponseEntity<Vuelo> obtenerVuelo(@PathVariable long id);

    @PostMapping("/{id}")
    ResponseEntity<Error> reservarVuelo(@PathVariable long id);
}