package com.maria.microservicio_vuelo.controller.repository;

import com.maria.microservicio_vuelo.controller.entity.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {
    @Query("SELECT v FROM Vuelo v WHERE plazas > 0")
    List<Vuelo> obtenerVuelosDisponibles();
}