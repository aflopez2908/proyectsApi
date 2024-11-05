package gestorfreelance.gestorfreelancev5.controller;

import gestorfreelance.gestorfreelancev5.model.Rol;
import gestorfreelance.gestorfreelancev5.model.Usuario;
import gestorfreelance.gestorfreelancev5.service.RolService;
import gestorfreelance.gestorfreelancev5.service.UsuarioService;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createUser")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        System.out.println(usuario);
        usuarioService.crearUsuario(usuario);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al crear el usuario: ");
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

    @GetMapping("/buscar/rol")
    public ResponseEntity<?> buscarPorRol(@RequestParam Integer rolId) {
        try {
            Rol rol = rolService.obtenerPorId(rolId);
            if (rol == null) {
                return ResponseEntity.badRequest().body("Error: No se encontró el rol con ID: " + rolId);
            }

            List<Usuario> usuarios = usuarioService.buscarPorRol(rol);
            if (usuarios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: No se encontraron usuarios para el rol con ID: " + rolId);
            }
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar usuarios por rol: " + e.getMessage());
        }
    }

}
