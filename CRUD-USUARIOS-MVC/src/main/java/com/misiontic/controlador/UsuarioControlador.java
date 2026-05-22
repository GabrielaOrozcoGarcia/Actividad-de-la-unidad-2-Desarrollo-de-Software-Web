package com.misiontic.controlador;

import com.misiontic.modelo.Usuario;
import com.misiontic.servicio.IUsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    private IUsuarioServicio usuarioServicio;

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

    // Mostrar formulario de olvidé mi contraseña
    @GetMapping("/olvide-contrasena")
    public String mostrarOlvideContrasena() {
        return "usuarios/olvide-contrasena";
    }

    // Procesar recuperación de contraseña
    @PostMapping("/recuperar-contrasena")
    public String recuperarContrasena(
            @RequestParam String email,
            Model modelo) {
        usuarioServicio.recuperarContrasena(email);
        modelo.addAttribute("mensaje",
                "Si el correo está registrado en el sistema, " +
                        "recibirás un mensaje con tu contraseña temporal en breve.");
        return "usuarios/olvide-contrasena";
    }
}