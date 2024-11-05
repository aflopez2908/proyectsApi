package gestorfreelance.gestorfreelancev5.service;
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

    public List<Usuario> getAllUsuarios() {
        return usuariosRepository.findAll();
    }

    public Usuario obtenerPorId(Integer id) {
        return usuariosRepository.findById(id).orElse(null);
    }

    public Usuario guardarUsuario(Usuario usuario) {
       /* if (usuariosRepository.findByEmailAndRol(usuario.getEmail(), usuario.getRol()) != null) {
            throw new IllegalArgumentException("El email ya está registrado para este rol.");
        }

        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        if (usuario.getContraseña() == null || usuario.getContraseña().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es obligatoria.");
        }*/

/*        String rolNombre = usuario.getRol().getNombre();
        if ("COORDINADOR".equals(rolNombre)) {
        } else if ("DESARROLLADOR".equals(rolNombre)) {
        } else if ("CLIENTE".equals(rolNombre)) {
        }*/

        return usuariosRepository.save(usuario);
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuariosRepository.save(usuario);
    }

    public void eliminarUsuario(Integer id) {
        usuariosRepository.deleteById(id);
    }
    public boolean existeUsuario(Integer id) {
        return usuariosRepository.existsById(id);
    }
    public Usuario buscarPorEmail(String email) {
        return usuariosRepository.findByEmail(email);
    }
    public List<Usuario> buscarPorRol(Rol rol) {
        return usuariosRepository.findByRol(rol);
    }
    public List<Usuario> buscarPorNombre(String nombre) {
        return usuariosRepository.findByNombreContaining(nombre);
    }

}