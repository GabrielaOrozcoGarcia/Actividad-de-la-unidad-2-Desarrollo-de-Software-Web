package com.misiontic.servicio;

import com.misiontic.modelo.Mascota;
import java.util.List;

public interface IMascotaServicio {

    public List<Mascota> listarTodas();

    public void guardar(Mascota mascota);

    public Mascota buscarPorId(Long id);

    public void actualizar(Mascota mascota);

    public void eliminar(Long id);
}
