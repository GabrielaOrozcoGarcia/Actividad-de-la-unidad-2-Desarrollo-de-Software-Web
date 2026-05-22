package com.misiontic.servicio;

import com.misiontic.modelo.Mascota;
import com.misiontic.repositorio.MascotaRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MascotaServicioImp implements IMascotaServicio {

    @Autowired
    private MascotaRepositorio mascotaRepositorio;

    @Transactional(readOnly = true)
    @Override
    public List<Mascota> listarTodas() {
        return (List<Mascota>) mascotaRepositorio.findAll();
    }

    @Transactional
    @Override
    public void guardar(Mascota mascota) {
        mascotaRepositorio.save(mascota);
    }

    @Transactional
    @Override
    public void actualizar(Mascota mascota) {
        mascotaRepositorio.save(mascota);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        mascotaRepositorio.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Mascota buscarPorId(Long id) {
        Optional<Mascota> resultado = mascotaRepositorio.findById(id);
        return resultado.orElse(null);
    }
}