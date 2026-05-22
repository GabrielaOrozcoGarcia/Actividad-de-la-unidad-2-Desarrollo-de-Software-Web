package com.misiontic.servicio;

import com.misiontic.modelo.Usuario;
import com.misiontic.repositorio.UsuarioRepositorio;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    // L — Listar todos los usuarios
    public List<Usuario> listarTodos() {
        return (List<Usuario>) usuarioRepositorio.findAll();
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

    // R — Buscar por email
    public Usuario buscarPorEmail(String email) {
        Optional<Usuario> resultado = usuarioRepositorio.findByEmail(email);
        return resultado.orElse(null);
    }

    // U — Actualizar usuario
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

    // Recuperar contraseña: genera clave temporal, la guarda y envía el correo
    public void recuperarContrasena(String email) {
        Usuario usuario = buscarPorEmail(email);

        // Siempre mostramos el mismo mensaje aunque el email no exista
        // para evitar enumeración de usuarios
        if (usuario == null) {
            return;
        }

        // Generar clave temporal de 8 caracteres
        String claveTemp = generarClaveTemporal();

        // Hashear y guardar
        usuario.setClave(passwordEncoder.encode(claveTemp));
        usuarioRepositorio.save(usuario);

        // Enviar correo HTML
        enviarCorreoRecuperacion(usuario.getEmail(), usuario.getNombre(), claveTemp);
    }

    // Genera una clave temporal alfanumérica de 8 caracteres
    private String generarClaveTemporal() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder clave = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            clave.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return clave.toString();
    }

    // Envía el correo HTML con la clave temporal
    private void enviarCorreoRecuperacion(String email, String nombre, String claveTemp) {
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

            helper.setFrom("no-reply@mascotasapp.local");
            helper.setTo(email);
            helper.setSubject("Recuperación de contraseña - Mascotas App");

            String cuerpo = "<html><body style='font-family:Arial,sans-serif;background:#f4f4f4;margin:0;padding:0'>"
                    + "<div style='max-width:560px;margin:40px auto;background:#fff;border:1px solid #ddd;"
                    + "border-radius:6px;padding:32px'>"
                    + "<h2 style='color:#333;margin-top:0'>Hola, " + nombre + "</h2>"
                    + "<p style='color:#555'>Recibimos una solicitud de recuperación de contraseña "
                    + "para tu cuenta en <strong>Mascotas App</strong>.</p>"
                    + "<p style='color:#555'>Tu nueva contraseña temporal es:</p>"
                    + "<div style='font-size:1.4em;font-weight:bold;background:#f0f7ff;"
                    + "border:1px solid #99c8ff;border-radius:4px;padding:12px 20px;"
                    + "display:inline-block;letter-spacing:2px;margin:12px 0'>"
                    + claveTemp + "</div>"
                    + "<p style='color:#555'>Por seguridad, <strong>te recomendamos cambiar esta contraseña</strong> "
                    + "inmediatamente después de iniciar sesión.</p>"
                    + "<p style='color:#555'>Si no solicitaste este cambio, puedes ignorar este correo.</p>"
                    + "<hr style='border:none;border-top:1px solid #eee;margin-top:24px'>"
                    + "<p style='font-size:12px;color:#999'>Este es un correo automático, "
                    + "por favor no respondas a este mensaje.</p>"
                    + "</div></body></html>";

            helper.setText(cuerpo, true);
            mailSender.send(mensaje);

        } catch (Exception e) {
            System.err.println("Error enviando correo: " + e.getMessage());
        }
    }
}