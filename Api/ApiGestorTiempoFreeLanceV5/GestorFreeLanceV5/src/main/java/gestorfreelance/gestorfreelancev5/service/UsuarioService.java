package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.exception.EmailAlreadyRegisteredException;
import gestorfreelance.gestorfreelancev5.exception.InvalidRoleException;
import gestorfreelance.gestorfreelancev5.model.Rol;
import gestorfreelance.gestorfreelancev5.model.Usuario;
import gestorfreelance.gestorfreelancev5.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import gestorfreelance.gestorfreelancev5.exception.RoleIdMismatchException;

@Service
public class UsuarioService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private RolService rolService;

    public Usuario guardarUsuario(Usuario usuario) {
        // Verificar si el email ya está registrado
        if (usuariosRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new EmailAlreadyRegisteredException("El correo ingresado ya está registrado. Ingrese una dirección de email diferente.");
        }

        // Validar si el rol es válido
        validarRol(usuario.getRol());

        // Validar otros datos del usuario
        validarUsuario(usuario);

        usuario.setFechaCreacion(LocalDateTime.now());
        return usuariosRepository.save(usuario);
    }

    // Método para validar si el rol es válido y que el ID coincida con el nombre
    private void validarRol(Rol rol) {
        if (rol == null) {
            throw new IllegalArgumentException("El rol es obligatorio.");
        }

        // Mapear los roles permitidos a sus IDs correspondientes
        if (rol.getRolId() == 1 && !"Administrador".equalsIgnoreCase(rol.getNombre())) {
            throw new RoleIdMismatchException("Rol_ID o  Rol  incorrecto Verifique e intente nuevamente.");
        } else if (rol.getRolId() == 2 && !"Desarrollador".equalsIgnoreCase(rol.getNombre())) {
            throw new RoleIdMismatchException("Rol_ID o  Rol  incorrecto Verifique e intente nuevamente.");
        } else if (rol.getRolId() == 3 && !"Cliente".equalsIgnoreCase(rol.getNombre())) {
            throw new RoleIdMismatchException("Rol_ID o  Rol  incorrecto Verifique e intente nuevamente.");
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
