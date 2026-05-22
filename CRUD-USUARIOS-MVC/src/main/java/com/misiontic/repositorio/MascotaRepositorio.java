package com.misiontic.repositorio;


import com.misiontic.modelo.Mascota;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepositorio extends CrudRepository<Mascota, Long> {
    // Spring Data genera el CRUD
}