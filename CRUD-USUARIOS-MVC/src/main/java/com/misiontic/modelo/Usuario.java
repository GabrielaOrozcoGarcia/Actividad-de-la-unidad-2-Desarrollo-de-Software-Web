package com.misiontic.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(length = 20)
    private String cedula;

    @Column(nullable = false, length = 255)
    private String clave;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 150)
    private String email;

    public Usuario() {}

    public String getCedula()  { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getClave()   { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public String getNombre()  { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail()   { return email; }
    public void setEmail(String email) { this.email = email; }
}