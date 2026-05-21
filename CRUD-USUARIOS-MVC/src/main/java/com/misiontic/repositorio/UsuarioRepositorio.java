package com.misiontic.repositorio;

import com.misiontic.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    // Spring Data JPA genera el CRUD
    // La primary key es String (cedula)
}
