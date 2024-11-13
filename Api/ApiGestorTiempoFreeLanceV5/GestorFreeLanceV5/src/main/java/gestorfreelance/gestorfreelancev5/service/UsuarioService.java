package gestorfreelance.gestorfreelancev5.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import gestorfreelance.gestorfreelancev5.model.IntentoLogin;
import gestorfreelance.gestorfreelancev5.model.Rol;
import gestorfreelance.gestorfreelancev5.model.Usuario;
import gestorfreelance.gestorfreelancev5.repository.IntentoLoginRepository;
import gestorfreelance.gestorfreelancev5.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private IntentoLoginRepository intentoLoginRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Inyectamos el PasswordEncoder


    public Usuario createUsuario(Usuario usuario) {
        usuario.setFechaCreacion(LocalDateTime.now());
        String Password = usuario.getContraseña();
        usuario.setContraseña(passwordEncoder.encode(Password));
        return usuariosRepository.save(usuario);
    }

    public Usuario saveUsuario(Usuario usuario) {
        Usuario nuevoUsuario = usuariosRepository.save(usuario);

        // Enviar correo de bienvenida
        String emailBody = "¡Bienvenido(a) a GPF!\n\n"
                + "Hola, " + usuario.getNombre() + ":\n\n"
                + "¡Bienvenido(a) a GPF! Estamos muy contentos de que te hayas unido a nosotros. "
                + "Ahora que formas parte de nuestra comunidad, podrás disfrutar de todas las funcionalidades y recursos "
                + "que hemos diseñado para ayudarte. ¡Nos alegra que estés con nosotros y esperamos que aproveches al máximo tu experiencia en nuestra plataforma!\n\n"
                + "Saludos cordiales,\n\n"
                + "El equipo de GPF";

        emailService.sendEmailwithAttachment(usuario.getEmail(), "Cuenta creada: " + usuario.getNombre() + " BIENVENID@:", emailBody);

        return nuevoUsuario;
    }


    public Usuario actualizarUsuario(Integer id, Usuario usuarioActualizado) {
        try {

            Usuario usuarioExistente = usuariosRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));


            if (usuarioActualizado.getNombre() == null || usuarioActualizado.getNombre().isEmpty()) {
                throw new IllegalArgumentException("El nombre es obligatorio.");
            }
            if (usuarioActualizado.getEmail() == null || usuarioActualizado.getEmail().isEmpty()) {
                throw new IllegalArgumentException("El correo electrónico es obligatorio.");
            }

            Optional<Usuario> usuarioConEmail = usuariosRepository.findByEmail(usuarioActualizado.getEmail());
            if (usuarioConEmail.isPresent() && !usuarioConEmail.get().getUsuarioId().equals(id)) {
                throw new IllegalArgumentException("El correo electrónico ya está registrado. Por favor, utilice otro.");
            }


            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setEmail(usuarioActualizado.getEmail());
            usuarioExistente.setRol(usuarioActualizado.getRol());


            if (usuarioActualizado.getContraseña() != null && !usuarioActualizado.getContraseña().isEmpty()) {
                String nuevaContraseña = passwordEncoder.encode(usuarioActualizado.getContraseña());
                usuarioExistente.setContraseña(nuevaContraseña);
            }


            return usuariosRepository.save(usuarioExistente);

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el usuario: " + e.getMessage(), e);
        }
    }


    public void eliminarUsuario(Integer id) {
        Optional<Usuario> usuarioOpt = usuariosRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuariosRepository.deleteById(id);


            String emailBody = "Hola " + usuario.getNombre() + ", tu cuenta ha sido eliminada.";
            emailService.sendEmailwithAttachment(usuario.getEmail(), "Cuenta eliminada", emailBody);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }
    }


    public List<Usuario> getAllUsuarios() {
        return usuariosRepository.findAll();
    }


    public Usuario obtenerPorId(Integer id) {
        return usuariosRepository.findById(id).orElse(null);
    }


    public boolean existeUsuario(Integer id) {
        return usuariosRepository.existsById(id);
    }


    public Optional<Usuario> buscarPorEmail(String email) {
        return usuariosRepository.findByEmail(email);
    }

    public List<Usuario> buscarPorRol(Rol rol) {
        return usuariosRepository.findByRol(rol);
    }


    public List<Usuario> buscarPorNombre(String nombre) {
        return usuariosRepository.findByNombreContaining(nombre);
    }

    public String unlockrUsuario(Usuario usuario) {
        Optional<IntentoLogin> intentoOpt = intentoLoginRepository.findByUsuarioAndVigenteTrue(usuario);

        if (intentoOpt.isPresent()) {
            IntentoLogin intento = intentoOpt.get();
            intento.setVigente(false); // Cambiar la vigencia a 0
/*            intento.setBloqueado(false); // Desbloquear al usuario
            intento.setContador(0); // Reiniciar el contador de intentos*/

            intentoLoginRepository.save(intento);

         /*   // Enviar correo de desbloqueo
            String emailBody = "¡HOLA DE NUEVO!\n\n"
                    + " " + usuario.getNombre() + ":\n\n"
                    + "En GPF! Estamos muy contentos de que te tu cuenta haya sido reacctivada. "
                    + "Ahora puedes continuar siendo parte de nuestra comunidad y podrás disfrutar de todas las funcionalidades y recursos "//        + "que hemos diseñado para ayudarte. ¡Nos alegra que estés de nuevo  con nosotros y esperamos que aproveches al máximo tu experiencia en nuestra plataforma!\n\n"
                    + "Saludos cordiales,\n\n"
                    + "El equipo de GPF";

            emailService.sendEmailwithAttachment(usuario.getEmail(), "!!CUENTA DESBLOQUEADA!!!" + usuario.getNombre() + " DESBLOQUEADO :", emailBody);   */

            return "Usuario desbloqueado correctamente.";
        } else {
            return "No se encontraron intentos vigentes para el usuario.";
        }
    }
}
