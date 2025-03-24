package com.maria.microservicio_reserva.dto;

public record ReservaDto(
        String nombre,
        String dni,
        long idHotel,
        long idVuelo
) {}