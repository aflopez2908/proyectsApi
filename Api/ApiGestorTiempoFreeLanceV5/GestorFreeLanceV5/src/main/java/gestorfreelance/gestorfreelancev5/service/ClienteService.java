package gestorfreelance.gestorfreelancev5.service;


import gestorfreelance.gestorfreelancev5.model.Cliente;
import gestorfreelance.gestorfreelancev5.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClientesRepository clienteRepository;

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Integer id) {
        return clienteRepository.findById(id);
    }

    public Cliente updateCliente(Integer id, Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));

        cliente.setNombre(clienteDetails.getNombre());
        cliente.setEmail(clienteDetails.getEmail());
        cliente.setTelefono(clienteDetails.getTelefono());
        cliente.setDireccion(clienteDetails.getDireccion());

        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
        clienteRepository.delete(cliente);
    }
}
