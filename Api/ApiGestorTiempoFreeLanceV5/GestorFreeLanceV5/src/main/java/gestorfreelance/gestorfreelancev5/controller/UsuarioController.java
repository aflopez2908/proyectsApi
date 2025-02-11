package gestorfreelance.gestorfreelancev5.controller;

import gestorfreelance.gestorfreelancev5.DTO.CreateUserDTO;
import gestorfreelance.gestorfreelancev5.model.*;
import gestorfreelance.gestorfreelancev5.repository.RolesRepository;
import gestorfreelance.gestorfreelancev5.service.RolService;
import gestorfreelance.gestorfreelancev5.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.*;
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

/*    @PreAuthorize("hasRole('ADMIN')")
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
    }*/

    @PreAuthorize("hasRole('ADMIN')")
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


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        try {
            Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuario);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuario actualizado exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error de integridad de datos. Verifique que el correo electrónico o el rol no estén duplicados.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo completar la actualización. Por favor, inténtelo de nuevo más tarde.");
        }
    }



    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario con ID: " + id + ". Detalles: " + e.getMessage());
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


    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<Usuario>> buscarPorNombre(@RequestParam String nombre) {
        List<Usuario> usuarios = usuarioService.buscarPorNombre(nombre);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/buscar/email")
    public ResponseEntity<?> buscarPorEmail(@RequestParam String email) {
        try {
            Optional<Usuario> usuario = usuarioService.buscarPorEmail(email);
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Usuario no encontrado con el email: " + email);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar el usuario por email: " + e.getMessage());
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/unlock")
    public ResponseEntity<?> unlockUsuario(@RequestBody Usuario usuario) {
        try {
            String resultado = usuarioService.unlockrUsuario(usuario);
            Map<String, String> response = new HashMap<>();
            response.put("message", resultado);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error al desbloquear el usuario: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






















}
