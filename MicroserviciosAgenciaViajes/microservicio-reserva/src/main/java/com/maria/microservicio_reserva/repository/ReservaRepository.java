package com.maria.microservicio_reserva.repository;

import com.maria.microservicio_reserva.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {}