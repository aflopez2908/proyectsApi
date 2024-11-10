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
        return nuevoUsuario;
    }


    public List<Usuario> getAllUsuarios() {
        return usuariosRepository.findAll();
    }

    public Usuario obtenerPorId(Integer id) {
        return usuariosRepository.findById(id).orElse(null);
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
            return "Usuario desbloqueado correctamente.";
        } else {
            return "No se encontraron intentos vigentes para el usuario.";
        }
    }



}