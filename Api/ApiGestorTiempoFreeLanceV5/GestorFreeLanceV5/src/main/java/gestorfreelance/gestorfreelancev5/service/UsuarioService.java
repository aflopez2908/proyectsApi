package gestorfreelance.gestorfreelancev5.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import gestorfreelance.gestorfreelancev5.model.Rol;
import gestorfreelance.gestorfreelancev5.model.Usuario;
import gestorfreelance.gestorfreelancev5.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    public Usuario createUsuario(Usuario usuario) {
        usuario.setFechaCreacion(LocalDateTime.now());
        String Password = usuario.getContraseña();
        usuario.setContraseña(new BCryptPasswordEncoder().encode(Password ));
        return usuariosRepository.save(usuario);
    }
    public Usuario saveUsuario(Usuario usuario) {
        return usuariosRepository.save(usuario);
    }


    public List<Usuario> getAllUsuarios() {
        return usuariosRepository.findAll();
    }

    public Usuario obtenerPorId(Integer id) {
        return usuariosRepository.findById(id).orElse(null);
    }


    public void eliminarUsuario(Integer id) {
        usuariosRepository.deleteById(id);
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

}