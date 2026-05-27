package com.misiontic.controlador;

import com.misiontic.modelo.Mascota;
import com.misiontic.servicio.IMascotaServicio;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mascotas")
public class MascotaControlador {

    @Autowired
    private IMascotaServicio mascotaServicio;

    // L — Listar mascotas
    @GetMapping
    public String listar(Model modelo) {

        List<Mascota> mascotas = mascotaServicio.listarTodas();

        modelo.addAttribute("mascotas", mascotas);

        return "mascotas/lista";
    }

    // C — Mostrar formulario nuevo
    @GetMapping("/agregar")
    public String agregar(Mascota mascota) {

        return "mascotas/formulario";
    }

    // C — Guardar nueva mascota con validacion
    @PostMapping("/guardar")
    public String guardar(@Valid Mascota mascota,
                          Errors errores) {

        if (errores.hasErrors()) {
            return "mascotas/formulario";
        }

        mascotaServicio.guardar(mascota);

        return "redirect:/mascotas";
    }

    // R — Ver detalle
    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Long id,
                      Model modelo) {

        Mascota mascota = mascotaServicio.buscarPorId(id);

        if (mascota == null) {
            return "redirect:/mascotas";
        }

        modelo.addAttribute("mascota", mascota);

        return "mascotas/detalle";
    }

    // U — Mostrar formulario editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id,
                         Model modelo) {

        Mascota mascota = mascotaServicio.buscarPorId(id);

        if (mascota == null) {
            return "redirect:/mascotas";
        }

        modelo.addAttribute("mascota", mascota);

        return "mascotas/formulario";
    }

    // U — Actualizar mascota con validacion
    @PostMapping("/actualizar")
    public String actualizar(@Valid Mascota mascota,
                             Errors errores) {

        if (errores.hasErrors()) {
            return "mascotas/formulario";
        }

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