package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.model.*;
import gestorfreelance.gestorfreelancev5.repository.IntentoLoginRepository;
import gestorfreelance.gestorfreelancev5.repository.UsuariosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuariosRepository usuariosRepository;

    @Mock
    private IntentoLoginRepository intentoLoginRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configuración de un rol para el test
        Rol rolUser = new Rol();
        rolUser.setRolId(1);
        rolUser.setNombre(ERol.USER);

        // Configuración de un usuario de prueba con el rol
        usuario = Usuario.builder()
                .usuarioId(1)
                .nombre("Test User")
                .email("test@example.com")
                .contraseña("123456")
                .fechaCreacion(LocalDateTime.now())
                .rol(Set.of(rolUser))
                .build();
    }

    @Test
    void testCreateUsuario() {
        when(usuariosRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario result = usuarioService.createUsuario(usuario);

        assertNotNull(result);
        assertEquals("Test User", result.getNombre());
        assertEquals("test@example.com", result.getEmail());
        verify(usuariosRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testSaveUsuario() {
        when(usuariosRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario result = usuarioService.saveUsuario(usuario);

        assertNotNull(result);
        assertEquals("Test User", result.getNombre());
        verify(emailService, times(1)).sendEmailwithAttachment(
                eq("test@example.com"),
                anyString(),
                contains("Bienvenido(a) a GPF")
        );
    }

    @Test
    void testEliminarUsuario() {
        when(usuariosRepository.findById(anyInt())).thenReturn(Optional.of(usuario));

        usuarioService.eliminarUsuario(usuario.getUsuarioId());

        verify(usuariosRepository, times(1)).deleteById(usuario.getUsuarioId());
        verify(emailService, times(1)).sendEmailwithAttachment(
                eq(usuario.getEmail()),
                eq("Cuenta eliminada"),
                contains("tu cuenta ha sido eliminada")
        );
    }

    @Test
    void testBuscarPorEmail() {
        when(usuariosRepository.findByEmail(anyString())).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = usuarioService.buscarPorEmail("test@example.com");

        assertTrue(result.isPresent());
        assertEquals("Test User", result.get().getNombre());
        verify(usuariosRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testUnlockUsuario() {
        IntentoLogin intentoLogin = new IntentoLogin();
        intentoLogin.setUsuario(usuario);
        intentoLogin.setVigente(true);

        when(intentoLoginRepository.findByUsuarioAndVigenteTrue(any(Usuario.class))).thenReturn(Optional.of(intentoLogin));

        String result = usuarioService.unlockrUsuario(usuario);

        assertEquals("Usuario desbloqueado correctamente.", result);
        assertFalse(intentoLogin.isVigente());
        verify(intentoLoginRepository, times(1)).save(any(IntentoLogin.class));
    }
}
