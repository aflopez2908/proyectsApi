package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.exception.CorreoUsuarioNoDisponibleException;
import gestorfreelance.gestorfreelancev5.exception.EmailAlreadyRegisteredException;
import gestorfreelance.gestorfreelancev5.exception.InvalidRoleException;
import gestorfreelance.gestorfreelancev5.exception.RoleIdMismatchException;
import gestorfreelance.gestorfreelancev5.model.Rol;
import gestorfreelance.gestorfreelancev5.model.Usuario;
import gestorfreelance.gestorfreelancev5.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private RolService rolService;

    @Autowired
    private EmailService emailService; // Inyectar EmailService

    public Usuario guardarUsuario(Usuario usuario) {
        // Verificar si el email ya está registrado
        if (usuariosRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new EmailAlreadyRegisteredException("El correo ingresado ya está registrado. Ingrese una dirección de email diferente.");
        }

        // Validar el rol y demás datos del usuario
        validarRol(usuario.getRol());
        validarUsuario(usuario);

        // Asignar la fecha de creación
        usuario.setFechaCreacion(LocalDateTime.now());

        // Guardar el usuario en la base de datos
        Usuario usuarioGuardado = usuariosRepository.save(usuario);

        // Enviar correo de bienvenida
        try {
            enviarCorreoBienvenida(usuarioGuardado);
        } catch (CorreoUsuarioNoDisponibleException e) {
            // Manejo de la excepción en caso de que el correo no esté disponible
            // Aquí podrías decidir si lanzar la excepción o continuar sin enviar el correo.
            System.err.println("Error enviando correo: " + e.getMessage());
        }

        return usuarioGuardado;
    }


    private void enviarCorreoBienvenida(Usuario usuario) throws CorreoUsuarioNoDisponibleException {
        String to = usuario.getEmail();
        if (to == null || to.isEmpty()) {
            throw new CorreoUsuarioNoDisponibleException("El correo del usuario no está disponible para enviar la notificación.");
        }

        String subject = "Bienvenido a nuestro sistema";
        String body = "Hola " + usuario.getNombre() + ",\n\n" +
                "Tu cuenta ha sido creada exitosamente. Aquí están los detalles:\n" +
                "Nombre de usuario: " + usuario.getNombre() + "\n" +
                "Correo electrónico: " + usuario.getEmail() + "\n\n" +
                "Si tienes alguna pregunta o problema, no dudes en contactarnos.\n\n" +
                "Saludos,\n" +
                "El equipo de soporte";

        emailService.sendEmailwithAttachment(to, subject, body);
    }



    public void eliminarUsuario(Integer id) {
        if (!usuariosRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado con el id: " + id);
        }
        usuariosRepository.deleteById(id);
    }




    private void validarRol(Rol rol) {
        if (rol == null) {
            throw new IllegalArgumentException("El rol es obligatorio.");
        }


        if (rol.getRolId() == 1 && !"Administrador".equalsIgnoreCase(rol.getNombre())) {
            throw new RoleIdMismatchException("Rol_ID o Rol incorrecto. Verifique e intente nuevamente.");
        } else if (rol.getRolId() == 2 && !"Desarrollador".equalsIgnoreCase(rol.getNombre())) {
            throw new RoleIdMismatchException("Rol_ID o Rol incorrecto. Verifique e intente nuevamente.");
        } else if (rol.getRolId() == 3 && ! "Cliente".equalsIgnoreCase(rol.getNombre())) {
            throw new RoleIdMismatchException("Rol_ID o Rol incorrecto. Verifique e intente nuevamente.");
        } else if (rol.getRolId() < 1 || rol.getRolId() > 3) {
            throw new InvalidRoleException("El ID de rol es inválido. Debe ser 1 (Administrador), 2 (Desarrollador) o 3 (Cliente).");
        }
    }

    private void validarUsuario(Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        if (usuario.getContraseña() == null || usuario.getContraseña().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es obligatoria.");
        }
    }




}
