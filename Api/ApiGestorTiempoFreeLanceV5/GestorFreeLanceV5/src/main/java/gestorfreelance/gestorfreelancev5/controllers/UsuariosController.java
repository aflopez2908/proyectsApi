package gestorfreelance.gestorfreelancev5.controllers;

import gestorfreelance.gestorfreelancev5.services.RolesService;
import gestorfreelance.gestorfreelancev5.services.UsuariosService;
import gestorfreelance.gestorfreelancev5.model.Roles;
import gestorfreelance.gestorfreelancev5.model.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private RolesService rolesService;

/*    @PostMapping("/create")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuarios usuario) {
        *//*try {
           // Roles rol = rolesService.obtenerPorId(usuario.getId());
*//**//*            if (rol == null) {
                return ResponseEntity.badRequest().body("Error: No se encontró el rol con ID: " + usuario.getRol() + usuario.getId());
            }*//**//*
        //    Usuarios nuevoUsuario = usuariosService.crearUsuario(usuario, rol);
         //   return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el usuario: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al crear el usuario: " + e.getMessage());
        }*//*
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al crear el usuario: " + e.getMessage());
    }*/

    @GetMapping
    public ResponseEntity<List<Usuarios>> obtenerTodos() {
        List<Usuarios> usuarios = usuariosService.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> obtenerPorId(@PathVariable Integer id) {
        Usuarios usuario = usuariosService.obtenerPorId(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
        try {
            usuariosService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario con ID: " + id + ". Detalles: " + e.getMessage());
        }
    }
    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<Usuarios>> buscarPorNombre(@RequestParam String nombre) {
        List<Usuarios> usuarios = usuariosService.buscarPorNombre(nombre);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/buscar/email")
    public ResponseEntity<?> buscarPorEmail(@RequestParam String email) {
        try {
            Usuarios usuario = usuariosService.buscarPorEmail(email);
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
            Roles rol = rolesService.obtenerPorId(rolId);
            if (rol == null) {
                return ResponseEntity.badRequest().body("Error: No se encontró el rol con ID: " + rolId);
            }

            List<Usuarios> usuarios = usuariosService.buscarPorRol(rol);
            if (usuarios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: No se encontraron usuarios para el rol con ID: " + rolId);
            }
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar usuarios por rol: " + e.getMessage());
        }
    }

}
