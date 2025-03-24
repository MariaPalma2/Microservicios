package com.maria.microservicio_reserva.controller;

import com.maria.microservicio_reserva.dto.ReservaDto;
import com.maria.microservicio_reserva.entity.Hotel;
import com.maria.microservicio_reserva.entity.Reserva;
import com.maria.microservicio_reserva.entity.Vuelo;
import com.maria.microservicio_reserva.feign.HotelFeign;
import com.maria.microservicio_reserva.feign.VueloFeign;
import com.maria.microservicio_reserva.repository.ReservaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HotelFeign hotelFeign;

    @Autowired
    private VueloFeign vueloFeign;

    @PostMapping
    public ResponseEntity<String> reservar(@RequestBody ReservaDto reservaDto) {
        try {
            // Reservar hotel
            this.hotelFeign.reservarHotel(reservaDto.idHotel());
            // Reservar vuelo
            this.vueloFeign.reservarVuelo(reservaDto.idVuelo());
        } catch (FeignException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.contentUTF8());
        }
        Reserva reserva = new Reserva();
        Hotel hotel = this.hotelFeign.obtenerHotel(reservaDto.idHotel()).getBody();
        Vuelo vuelo = this.vueloFeign.obtenerVuelo(reservaDto.idVuelo()).getBody();

        reserva.setNombre(reservaDto.nombre());
        reserva.setDni(reservaDto.dni());
        reserva.setHotel(hotel);
        reserva.setVuelo(vuelo);
        this.reservaRepository.saveAndFlush(reserva);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}