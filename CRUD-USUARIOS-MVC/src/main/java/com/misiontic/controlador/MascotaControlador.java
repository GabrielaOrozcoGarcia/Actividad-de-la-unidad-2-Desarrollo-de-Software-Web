package com.misiontic.controlador;


import com.misiontic.modelo.Mascota;
import com.misiontic.servicio.MascotaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mascotas")
public class MascotaControlador {

    @Autowired
    private MascotaServicio mascotaServicio;

    // L — Listar mascotas
    @GetMapping
    public String listar(Model modelo) {
        List<Mascota> mascotas = mascotaServicio.listarTodas();
        modelo.addAttribute("mascotas", mascotas);
        return "mascotas/lista";
    }

    // C — Mostrar formulario de creación
    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model modelo) {
        modelo.addAttribute("mascota", new Mascota());
        return "mascotas/formulario";
    }

    // C — Guardar nueva mascota
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Mascota mascota) {
        mascotaServicio.guardar(mascota);
        return "redirect:/mascotas";
    }

    // R — Ver detalle de mascota
    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Long id, Model modelo) {
        Mascota mascota = mascotaServicio.buscarPorId(id);
        if (mascota == null) {
            return "redirect:/mascotas";
        }
        modelo.addAttribute("mascota", mascota);
        return "mascotas/detalle";
    }

    // U — Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model modelo) {
        Mascota mascota = mascotaServicio.buscarPorId(id);
        if (mascota == null) {
            return "redirect:/mascotas";
        }
        modelo.addAttribute("mascota", mascota);
        return "mascotas/formulario";
    }

    // U — Actualizar mascota
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Mascota mascota) {
        mascotaServicio.actualizar(mascota);
        return "redirect:/mascotas";
    }

    // D — Eliminar mascota
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        mascotaServicio.eliminar(id);
        return "redirect:/mascotas";
    }
}
