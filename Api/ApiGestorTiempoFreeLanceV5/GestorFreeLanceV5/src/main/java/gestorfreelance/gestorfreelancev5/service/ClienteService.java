package gestorfreelance.gestorfreelancev5.service;


import gestorfreelance.gestorfreelancev5.model.Ciudad;
import gestorfreelance.gestorfreelancev5.model.Cliente;
import gestorfreelance.gestorfreelancev5.model.Direccion;
import gestorfreelance.gestorfreelancev5.model.Pais;
import gestorfreelance.gestorfreelancev5.repository.CiudadesRepository;
import gestorfreelance.gestorfreelancev5.repository.ClientesRepository;
import gestorfreelance.gestorfreelancev5.repository.DireccionesRepository;
import gestorfreelance.gestorfreelancev5.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClientesRepository clienteRepository;

    @Autowired
    private CiudadesRepository ciudadRepository;

    @Autowired
    private DireccionesRepository direccionRepository;

    @Autowired
    private PaisRepository paisRepository;

    public Cliente createCliente(Cliente cliente) {
        if (cliente.getDireccion() != null) {
            Direccion direccion = cliente.getDireccion();
            Optional<Direccion> direccionExistente = direccionRepository.findByCalleContaining(direccion.getCalle());
            System.out.println("Direccion: " + direccionExistente);
            Ciudad ciudad = cliente.getDireccion().getCiudad();
            List<Ciudad> ciudadExistente = ciudadRepository.findByNombre(ciudad.getNombre());
            System.out.println("Ciudad: " + ciudadExistente);
            Pais pais = ciudad.getPais();
            Optional<Pais> paisExistente = paisRepository.findByNombre(pais.getNombre());
            System.out.println("Pais: " + paisExistente);
            if (paisExistente.isEmpty()) {pais = paisRepository.save(pais);}
            if (ciudadExistente.isEmpty()) {ciudad = ciudadRepository.save(ciudad); ciudad.setPais(pais);}
            if (direccionExistente.isEmpty()) {direccion = direccionRepository.save(direccion); direccion.setCiudad(ciudad);}
            if (direccion.getDireccionId() == null) {
                direccion.setDireccionId(direccionExistente.get().getDireccionId());
                direccion.setCiudad(ciudadExistente.get(0));
            }
            System.out.println("Direccion: " + direccion);
            cliente.setDireccion(direccion);
            System.out.println("Cliente: " + cliente);
        }
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
