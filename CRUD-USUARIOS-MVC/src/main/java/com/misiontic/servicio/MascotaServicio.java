package com.misiontic.servicio;


import com.misiontic.modelo.Mascota;
import com.misiontic.repositorio.MascotaRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MascotaServicio {

    @Autowired
    private MascotaRepositorio mascotaRepositorio;

    // Listar todas las mascotas
    public List<Mascota> listarTodas() {
        return (List<Mascota>) mascotaRepositorio.findAll();
    }

    // Guardar nueva mascota
    public void guardar(Mascota mascota) {
        mascotaRepositorio.save(mascota);
    }

    // Buscar por ID
    public Mascota buscarPorId(Long id) {
        Optional<Mascota> resultado = mascotaRepositorio.findById(id);
        return resultado.orElse(null);
    }

    // Actualizar mascota
    public void actualizar(Mascota mascota) {
        mascotaRepositorio.save(mascota);
    }

    // Eliminar por ID
    public void eliminar(Long id) {
        mascotaRepositorio.deleteById(id);
    }
}