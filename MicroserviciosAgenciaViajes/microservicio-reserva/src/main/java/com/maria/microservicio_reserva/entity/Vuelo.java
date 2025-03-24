package com.maria.microservicio_reserva.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vuelos")
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idvuelo")
    private long idVuelo;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String fecha;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private long plazas;

    public long getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(long idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public long getPlazas() {
        return plazas;
    }

    public void setPlazas(long plazas) {
        this.plazas = plazas;
    }
}