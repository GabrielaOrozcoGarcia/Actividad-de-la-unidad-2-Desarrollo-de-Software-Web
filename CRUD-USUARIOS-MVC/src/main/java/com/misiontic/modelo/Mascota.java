package com.misiontic.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotEmpty
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotEmpty
    @Column(nullable = false, length = 10)
    private String genero;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Double peso;

    @NotEmpty
    @Column(nullable = false, length = 20)
    private String tamano;

    @NotEmpty
    @Column(nullable = false, length = 50)
    private String color;

    @NotEmpty
    @Column(nullable = false, length = 100)
    private String raza;

    @NotEmpty
    @Column(nullable = false, length = 50)
    private String especie;

    @NotEmpty
    @Column(name = "fecha_nacimiento", nullable = false, length = 10)
    private String fechaNacimiento;

    @NotEmpty
    @Column(nullable = false, length = 100)
    private String propietario;

    @NotEmpty
    @Column(name = "domestico_o_salvaje", nullable = false, length = 20)
    private String domesticoOSalvaje;

    @Column(name = "tiene_vacunas")
    private Boolean tieneVacunas = false;

    @NotEmpty
    @Column(nullable = false, length = 100)
    private String veterinario;
}