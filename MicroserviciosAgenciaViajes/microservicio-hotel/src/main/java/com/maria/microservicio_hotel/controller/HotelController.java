package com.maria.microservicio_hotel.controller;

import com.maria.microservicio_hotel.entity.Hotel;
import com.maria.microservicio_hotel.repository.HotelRepository;
import com.maria.microservicio_hotel.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hoteles")
public class HotelController {
    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping
    public ResponseEntity<List<Hotel>> obtenerHotelesDisponibles() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.hotelRepository.obtenerHotelesDisponibles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerHotel(@PathVariable long id) {
        Optional<Hotel> hotelBuscado = this.hotelRepository.findById(id);
        if (hotelBuscado.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Error("No se encontró el hotel con ID " + id));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(hotelBuscado.get());
    }

    @PostMapping("/{id}")
    public ResponseEntity<Error> reservarHotel(@PathVariable long id) {
        Optional<Hotel> hotelBuscado = this.hotelRepository.findById(id);
        if (hotelBuscado.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Error("No se encontró el hotel con ID " + id));
        }
        Hotel hotelEncontrado = hotelBuscado.get();
        if (!hotelEncontrado.isDisponible()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Error("El hotel no está disponible. Elija otro"));
        }
        hotelEncontrado.setDisponible(false);
        this.hotelRepository.saveAndFlush(hotelEncontrado);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}