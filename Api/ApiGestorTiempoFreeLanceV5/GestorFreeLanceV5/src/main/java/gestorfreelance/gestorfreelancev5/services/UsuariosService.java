package gestorfreelance.gestorfreelancev5.services;
import java.util.List;

import gestorfreelance.gestorfreelancev5.model.Roles;
import gestorfreelance.gestorfreelancev5.model.Usuarios;
import gestorfreelance.gestorfreelancev5.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    public List<Usuarios> obtenerTodos() {
        return usuariosRepository.findAll();
    }

    public Usuarios obtenerPorId(Integer id) {
        return usuariosRepository.findById(id).orElse(null);
    }

    public Usuarios guardarUsuario(Usuarios usuario) {
        if (usuariosRepository.findByEmailAndRol(usuario.getEmail(), usuario.getRol()) != null) {
            throw new IllegalArgumentException("El email ya está registrado para este rol.");
        }

        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        if (usuario.getContraseña() == null || usuario.getContraseña().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es obligatoria.");
        }

        String rolNombre = usuario.getRol().getNombre();
        if ("COORDINADOR".equals(rolNombre)) {
        } else if ("DESARROLLADOR".equals(rolNombre)) {
        } else if ("CLIENTE".equals(rolNombre)) {
        }

        return usuariosRepository.save(usuario);
    }

    public Usuarios crearUsuario(Usuarios usuario, Roles rol) {
        usuario.setRol(rol);
        return guardarUsuario(usuario);
    }

    public void eliminarUsuario(Integer id) {
        usuariosRepository.deleteById(id);
    }
    public boolean existeUsuario(Integer id) {
        return usuariosRepository.existsById(id);
    }
    public Usuarios buscarPorEmail(String email) {
        return usuariosRepository.findByEmail(email);
    }
    public List<Usuarios> buscarPorRol(Roles rol) {
        return usuariosRepository.findByRol(rol);
    }
    public List<Usuarios> buscarPorNombre(String nombre) {
        return usuariosRepository.findByNombreContaining(nombre);
    }







}