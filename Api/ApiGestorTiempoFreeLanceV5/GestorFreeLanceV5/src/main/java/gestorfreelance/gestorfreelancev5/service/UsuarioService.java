package gestorfreelance.gestorfreelancev5.service;
import java.time.LocalDateTime;
import java.util.List;

import gestorfreelance.gestorfreelancev5.model.Rol;
import gestorfreelance.gestorfreelancev5.model.Usuario;
import gestorfreelance.gestorfreelancev5.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private RolService rolService;

    public List<Usuario> obtenerTodos() {
        return usuariosRepository.findAll();
    }

    public Usuario obtenerPorId(Integer id) {
        return usuariosRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
    }

    public Usuario guardarUsuario(Usuario usuario) {
        // Cambia findByEmail a devolver un Optional<Usuario>
        if (usuariosRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }

        validarUsuario(usuario);
        usuario.setFechaCreacion(LocalDateTime.now());
        return usuariosRepository.save(usuario);
    }

    public void eliminarUsuario(Integer id) {
        if (!usuariosRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }
        usuariosRepository.deleteById(id);
    }

    public Usuario buscarPorEmail(String email) {
        return usuariosRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el email: " + email));
    }


    public List<Usuario> buscarPorRol(Integer rolId) {
        Rol rol = rolService.obtenerPorId(rolId);
        if (rol == null) {
            throw new IllegalArgumentException("No se encontró el rol con ID: " + rolId);
        }

        List<Usuario> usuarios = usuariosRepository.findByRol(rol);
        if (usuarios.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron usuarios para el rol con ID: " + rolId);
        }

        return usuarios;
    }

    public List<Usuario> buscarPorNombre(String nombre) {
        return usuariosRepository.findByNombreContaining(nombre);
    }

    private void validarUsuario(Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        if (usuario.getContraseña() == null || usuario.getContraseña().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es obligatoria.");
        }

        if (usuario.getRol() == null) {
            throw new IllegalArgumentException("El rol es obligatorio.");
        }
    }
}
