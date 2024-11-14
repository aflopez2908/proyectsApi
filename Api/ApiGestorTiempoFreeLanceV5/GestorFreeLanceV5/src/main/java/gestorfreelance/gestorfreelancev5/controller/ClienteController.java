package gestorfreelance.gestorfreelancev5.controller;


import gestorfreelance.gestorfreelancev5.model.Cliente;
import gestorfreelance.gestorfreelancev5.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
@RequestMapping("/api/v1/cliente")
@Tag(name = "Cliente Management", description = "Operaciones relacionadas con la gestión de clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;


    @Operation(summary = "Create a new client", description = "Crea un nuevo cliente en el sistema")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente newCliente = clienteService.createCliente(cliente);
        return ResponseEntity.ok(newCliente);
    }
    @Operation(summary = "Get all clients", description = "Obtiene todos los clientes registrados")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.getAllClientes();
        return ResponseEntity.ok(clientes);
    }
    @Operation(summary = "Get client by ID", description = "Obtiene un cliente por su ID")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        return clienteService.getClienteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Update client by ID", description = "Actualiza la información de un cliente por su ID")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Integer id, @RequestBody Cliente clienteDetails) {
        Cliente updatedCliente = clienteService.updateCliente(id, clienteDetails);
        return ResponseEntity.ok(updatedCliente);
    }
    @Operation(summary = "Delete client by ID", description = "Elimina un cliente por su ID")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
