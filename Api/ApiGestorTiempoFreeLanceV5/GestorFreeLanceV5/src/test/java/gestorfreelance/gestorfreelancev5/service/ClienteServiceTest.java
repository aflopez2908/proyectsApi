package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.model.Cliente;
import gestorfreelance.gestorfreelancev5.repository.ClientesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClientesRepository clienteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("John Doe");

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente createdCliente = clienteService.createCliente(cliente);

        assertNotNull(createdCliente);
        assertEquals("John Doe", createdCliente.getNombre());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testGetAllClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("John Doe");
        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Jane Smith");

        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        List<Cliente> clientes = clienteService.getAllClientes();

        assertNotNull(clientes);
        assertEquals(2, clientes.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testGetClienteById() {
        Cliente cliente = new Cliente();
        cliente.setClienteId(1);
        cliente.setNombre("John Doe");

        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        Optional<Cliente> foundCliente = clienteService.getClienteById(1);

        assertTrue(foundCliente.isPresent());
        assertEquals("John Doe", foundCliente.get().getNombre());
        verify(clienteRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateCliente() {
        Cliente cliente = new Cliente();
        cliente.setClienteId(1);
        cliente.setNombre("John Doe");

        Cliente updatedDetails = new Cliente();
        updatedDetails.setNombre("Updated Name");

        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente updatedCliente = clienteService.updateCliente(1, updatedDetails);

        assertNotNull(updatedCliente);
        assertEquals("Updated Name", updatedCliente.getNombre());
        verify(clienteRepository, times(1)).findById(1);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testDeleteCliente() {
        Cliente cliente = new Cliente();
        cliente.setClienteId(1);

        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        doNothing().when(clienteRepository).delete(cliente);

        clienteService.deleteCliente(1);

        verify(clienteRepository, times(1)).findById(1);
        verify(clienteRepository, times(1)).delete(cliente);
    }
}