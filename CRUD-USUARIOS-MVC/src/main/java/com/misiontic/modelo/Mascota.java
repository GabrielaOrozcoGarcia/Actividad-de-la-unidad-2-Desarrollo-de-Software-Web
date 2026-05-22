package com.misiontic.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table(name = "mascotas")
public class Mascota implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 10)
    private String genero;

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false, length = 20)
    private String tamano;

    @Column(nullable = false, length = 50)
    private String color;

    @Column(nullable = false, length = 100)
    private String raza;

    @Column(nullable = false, length = 50)
    private String especie;

    @Column(name = "fecha_nacimiento", nullable = false, length = 10)
    private String fechaNacimiento;

    @Column(nullable = false, length = 100)
    private String propietario;

    @Column(name = "domestico_o_salvaje", nullable = false, length = 20)
    private String domesticoOSalvaje;

    @Column(name = "tiene_vacunas", nullable = false)
    private Boolean tieneVacunas;

    @Column(nullable = false, length = 100)
    private String veterinario;

}