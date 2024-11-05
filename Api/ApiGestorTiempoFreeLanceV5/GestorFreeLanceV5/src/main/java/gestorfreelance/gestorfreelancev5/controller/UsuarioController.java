package gestorfreelance.gestorfreelancev5.controller;

import gestorfreelance.gestorfreelancev5.DTO.CreateUserDTO;
import gestorfreelance.gestorfreelancev5.model.*;
import gestorfreelance.gestorfreelancev5.repository.RolesRepository;
import gestorfreelance.gestorfreelancev5.service.RolService;
import gestorfreelance.gestorfreelancev5.service.UsuarioService;
import jakarta.validation.Valid;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    public UsuarioController(PasswordEncoder passwordEncoder, UsuarioService usuarioService) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioService = usuarioService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody Usuario usuario) {
        try {
            Usuario user = usuarioService.createUsuario(usuario);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuario creado exitosamente");
            response.put("UserId", user.getUsuarioId().toString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser2(@Valid @RequestBody CreateUserDTO createUserDTO) {
        try {
            Set<Rol> rol = createUserDTO.getRol().stream()
                    .map(roleName -> rolesRepository.findByNombre(ERol.valueOf(roleName))
                            .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado")))
                    .collect(Collectors.toSet());

            Usuario usuario = Usuario.builder()
                    .nombre(createUserDTO.getNombre())
                    .contraseña(passwordEncoder.encode(createUserDTO.getContraseña()))
                    .email(createUserDTO.getEmail())
                    .fechaCreacion(LocalDateTime.now())
                    .rol(rol).build();

            usuarioService.saveUsuario(usuario);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuario creado exitosamente");
            response.put("UserId", usuario.getUsuarioId().toString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario con ID: " + id + ". Detalles: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<Usuario>> buscarPorNombre(@RequestParam String nombre) {
        List<Usuario> usuarios = usuarioService.buscarPorNombre(nombre);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/buscar/email")
    public ResponseEntity<?> buscarPorEmail(@RequestParam String email) {
        try {
            Usuario usuario = usuarioService.buscarPorEmail(email);
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Usuario no encontrado con el email: " + email);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar el usuario por email: " + e.getMessage());
        }
    }
}
