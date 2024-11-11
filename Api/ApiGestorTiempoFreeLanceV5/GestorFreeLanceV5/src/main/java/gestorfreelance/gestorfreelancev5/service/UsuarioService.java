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
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private IntentoLoginRepository intentoLoginRepository;

    @Autowired
    private EmailService emailService;

    public Usuario createUsuario(Usuario usuario) {
        usuario.setFechaCreacion(LocalDateTime.now());
        String Password = usuario.getContraseña();
        usuario.setContraseña(new BCryptPasswordEncoder().encode(Password));
        return usuariosRepository.save(usuario);
    }


    public Usuario saveUsuario(Usuario usuario) {
        Usuario nuevoUsuario = usuariosRepository.save(usuario);

        // BIENVENIDA
        String emailBody = "¡Bienvenido(a) a GPF!\n\n"
                + "Hola, " + usuario.getNombre() + ":\n\n"
                + "¡Bienvenido(a) a GPF! Estamos muy contentos de que te hayas unido a nosotros. "
                + "Ahora que formas parte de nuestra comunidad, podrás disfrutar de todas las funcionalidades y recursos "
                + "que hemos diseñado para ayudarte. ¡Nos alegra que estés con nosotros y esperamos que aproveches al máximo tu experiencia en nuestra plataforma!\n\n"
                + "Saludos cordiales,\n\n"
                + "El equipo de GPF";

        emailService.sendEmailwithAttachment(usuario.getEmail(), "Cuenta creada"+ usuario.getNombre() + " BIENVENID@:\n\n" , emailBody);

        return nuevoUsuario;
    }

    public Usuario actualizarUsuario(Integer id, Usuario usuarioActualizado) {
        try {
            // Verificar si el usuario existe
            Usuario usuarioExistente = usuariosRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

            // Validar información necesaria
            if (usuarioActualizado.getNombre() == null || usuarioActualizado.getNombre().isEmpty()) {
                throw new IllegalArgumentException("El nombre es obligatorio.");
            }
            if (usuarioActualizado.getEmail() == null || usuarioActualizado.getEmail().isEmpty()) {
                throw new IllegalArgumentException("El correo electrónico es obligatorio.");
            }
            if (usuarioActualizado.getContraseña() == null || usuarioActualizado.getContraseña().isEmpty()) {
                throw new IllegalArgumentException("La contraseña es obligatoria.");
            }

            // Validar que el correo no esté duplicado
            Optional<Usuario> usuarioConEmail = usuariosRepository.findByEmail(usuarioActualizado.getEmail());
            if (usuarioConEmail.isPresent() && !usuarioConEmail.get().getUsuarioId().equals(id)) {
                throw new IllegalArgumentException("El correo electrónico ya está registrado. Por favor, utilice otro.");
            }

            // Actualizar los datos del usuario
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setEmail(usuarioActualizado.getEmail());
            usuarioExistente.setContraseña(new BCryptPasswordEncoder().encode(usuarioActualizado.getContraseña()));
            usuarioExistente.setRol(usuarioActualizado.getRol());

            // Guardar cambios
            return usuariosRepository.save(usuarioExistente);

        } catch (Exception e) {
            // Captura cualquier otro error y genera una excepción personalizada
            throw new RuntimeException("Error al actualizar el usuario: " + e.getMessage(), e);
        }
    }




    public void eliminarUsuario(Integer id) {
        Optional<Usuario> usuarioOpt = usuariosRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuariosRepository.deleteById(id);

            // Enviar correo de notificación
            System.out.println("hola 1 ");
            String emailBody = "Hola " + usuario.getNombre() + ", tu cuenta ha sido eliminada.";
            emailService.sendEmailwithAttachment(usuario.getEmail(), "Cuenta eliminada", emailBody);
            System.out.println("hola 2 ");

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
            // corre correo desbloqueado
            return "Usuario desbloqueado correctamente.";
        } else {
            return "No se encontraron intentos vigentes para el usuario.";
        }
    }



    public void enviarCorreoAUsuarioEspecifico(Integer usuarioId, String asunto, String mensaje) {
        // Buscar el usuario por su ID
        Usuario usuario = usuariosRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + usuarioId));

        // Verificar que el usuario tenga un email válido
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new IllegalStateException("El usuario con ID: " + usuarioId + " no tiene un correo electrónico válido.");
        }

        // Enviar el correo utilizando el servicio de correo electrónico
        try {
            emailService.sendEmailwithAttachment(usuario.getEmail(), asunto, mensaje);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el correo a " + usuario.getEmail() + ": " + e.getMessage());
        }
    }






}