package com.misiontic.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mascotas")
public class Mascota {

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

    // Constructor vacío requerido por JPA
    public Mascota() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public String getTamano() { return tamano; }
    public void setTamano(String tamano) { this.tamano = tamano; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getPropietario() { return propietario; }
    public void setPropietario(String propietario) { this.propietario = propietario; }

    public String getDomesticoOSalvaje() { return domesticoOSalvaje; }
    public void setDomesticoOSalvaje(String domesticoOSalvaje) { this.domesticoOSalvaje = domesticoOSalvaje; }

    public Boolean getTieneVacunas() { return tieneVacunas; }
    public void setTieneVacunas(Boolean tieneVacunas) { this.tieneVacunas = tieneVacunas; }

    public String getVeterinario() { return veterinario; }
    public void setVeterinario(String veterinario) { this.veterinario = veterinario; }
}