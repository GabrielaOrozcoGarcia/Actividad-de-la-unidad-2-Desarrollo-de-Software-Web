package com.misiontic.repositorio;


import com.misiontic.modelo.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepositorio extends JpaRepository<Mascota, Long> {
    // Spring Data JPA genera el CRUD
}