package com.misiontic.servicio;

import com.misiontic.modelo.Usuario;
import com.misiontic.repositorio.UsuarioRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // L — Listar todos los usuarios
    public List<Usuario> listarTodos() {
        return usuarioRepositorio.findAll();
    }

    // C — Guardar nuevo usuario (hashea la clave)
    public void guardar(Usuario usuario) {
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        usuarioRepositorio.save(usuario);
    }

    // R — Buscar por cedula
    public Usuario buscarPorCedula(String cedula) {
        Optional<Usuario> resultado = usuarioRepositorio.findById(cedula);
        return resultado.orElse(null);
    }

    // U — Actualizar usuario
    // Si la clave viene vacía conserva la existente, si no la hashea
    public void actualizar(Usuario usuario) {
        Usuario existente = buscarPorCedula(usuario.getCedula());
        if (existente != null) {
            if (usuario.getClave() == null || usuario.getClave().isEmpty()) {
                usuario.setClave(existente.getClave());
            } else {
                usuario.setClave(passwordEncoder.encode(usuario.getClave()));
            }
        }
        usuarioRepositorio.save(usuario);
    }

    // D — Eliminar por cedula
    public void eliminar(String cedula) {
        usuarioRepositorio.deleteById(cedula);
    }
}
