package com.misiontic.controlador;

import com.misiontic.modelo.Usuario;
import com.misiontic.servicio.IUsuarioServicio;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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

    // C — Mostrar formulario nuevo  (Guía 10: ruta /agregar)
    @GetMapping("/agregar")
    public String agregar(Usuario usuario) {
        return "usuarios/formulario";
    }

    // C — Guardar nuevo usuario con validación  (Guía 13)
    @PostMapping("/guardar")
    public String guardar(@Valid Usuario usuario, Errors errores) {
        if (errores.hasErrors()) {
            return "usuarios/formulario";
        }
        usuarioServicio.guardar(usuario);
        return "redirect:/usuarios";
    }

    // U — Mostrar formulario editar  (Guía 11: ruta /editar/{cedula})
    @GetMapping("/editar/{cedula}")
    public String editar(@PathVariable String cedula, Model modelo) {
        Usuario usuario = usuarioServicio.buscarPorCedula(cedula);
        if (usuario == null) {
            return "redirect:/usuarios";
        }
        usuario.setClave("");
        modelo.addAttribute("usuario", usuario);
        return "usuarios/formulario";
    }

    // U — Actualizar usuario con validación  (Guía 13)
    @PostMapping("/actualizar")
    public String actualizar(@Valid Usuario usuario, Errors errores) {
        if (errores.hasErrors()) {
            return "usuarios/formulario";
        }
        usuarioServicio.actualizar(usuario);
        return "redirect:/usuarios";
    }

    // D — Eliminar usuario  (Guía 12: ruta /eliminar/{cedula})
    @GetMapping("/eliminar/{cedula}")
    public String eliminar(@PathVariable String cedula) {
        usuarioServicio.eliminar(cedula);
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