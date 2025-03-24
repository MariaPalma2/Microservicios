package com.maria.microservicio_vuelo.controller;

import com.maria.microservicio_vuelo.controller.entity.Vuelo;
import com.maria.microservicio_vuelo.controller.repository.VueloRepository;
import com.maria.microservicio_vuelo.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vuelos")
public class VueloController {
    @Autowired
    private VueloRepository vueloRepository;

    @GetMapping
    public ResponseEntity<List<Vuelo>> obtenerVuelosDisponibles() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.vueloRepository.obtenerVuelosDisponibles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerVuelo(@PathVariable long id) {
        Optional<Vuelo> vueloBuscado = this.vueloRepository.findById(id);
        if (vueloBuscado.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Error("No se encontró el vuelo con ID " + id));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vueloBuscado.get());
    }

    @PostMapping("/{id}")
    public ResponseEntity<Error> reservarVuelo(@PathVariable long id) {
        Optional<Vuelo> vueloBuscado = this.vueloRepository.findById(id);
        if (vueloBuscado.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Error("No se encontró el vuelo con el ID " + id));
        }
        Vuelo vueloEncontrado = vueloBuscado.get();
        if (vueloEncontrado.getPlazas() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Error("El vuelo seleccionado no tiene plazas disponibles"));
        }
        vueloEncontrado.setPlazas(vueloEncontrado.getPlazas() - 1);
        this.vueloRepository.save(vueloEncontrado);
        this.vueloRepository.flush();
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}