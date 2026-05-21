package com.misiontic.controlador;

import com.misiontic.modelo.Usuario;
import com.misiontic.servicio.UsuarioServicio;
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
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    // L — Listar usuarios
    @GetMapping
    public String listar(Model modelo) {
        List<Usuario> usuarios = usuarioServicio.listarTodos();
        modelo.addAttribute("usuarios", usuarios);
        return "usuarios/lista";
    }

    // C — Mostrar formulario nuevo
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model modelo) {
        modelo.addAttribute("usuario", new Usuario());
        return "usuarios/formulario";
    }

    // C — Guardar nuevo usuario
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario) {
        usuarioServicio.guardar(usuario);
        return "redirect:/usuarios";
    }

    // R — Ver detalle
    @GetMapping("/ver/{cedula}")
    public String ver(@PathVariable String cedula, Model modelo) {
        Usuario usuario = usuarioServicio.buscarPorCedula(cedula);
        if (usuario == null) {
            return "redirect:/usuarios";
        }
        modelo.addAttribute("usuario", usuario);
        return "usuarios/detalle";
    }

    // U — Mostrar formulario editar
    @GetMapping("/editar/{cedula}")
    public String mostrarFormularioEditar(@PathVariable String cedula, Model modelo) {
        Usuario usuario = usuarioServicio.buscarPorCedula(cedula);
        if (usuario == null) {
            return "redirect:/usuarios";
        }
        // Limpiamos la clave para que no se muestre el hash
        usuario.setClave("");
        modelo.addAttribute("usuario", usuario);
        return "usuarios/formulario";
    }

    // U — Actualizar usuario
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Usuario usuario) {
        usuarioServicio.actualizar(usuario);
        return "redirect:/usuarios";
    }

    // D — Eliminar usuario
    @GetMapping("/eliminar/{cedula}")
    public String eliminar(@PathVariable String cedula) {
        usuarioServicio.eliminar(cedula);
        return "redirect:/usuarios";
    }
}
