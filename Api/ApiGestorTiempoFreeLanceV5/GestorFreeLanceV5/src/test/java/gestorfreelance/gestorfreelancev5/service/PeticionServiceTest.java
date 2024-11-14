package gestorfreelance.gestorfreelancev5.service;
import gestorfreelance.gestorfreelancev5.DTO.PeticionDTO;
import gestorfreelance.gestorfreelancev5.exception.InvalidDataException;
import gestorfreelance.gestorfreelancev5.exception.ProyectoNoEncontradoException;
import gestorfreelance.gestorfreelancev5.exception.ProyectoTerminadoException;
import gestorfreelance.gestorfreelancev5.exception.ResourceNotFoundException;
import gestorfreelance.gestorfreelancev5.model.*;
import gestorfreelance.gestorfreelancev5.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class PeticionServiceTest {

    @Mock
    private PeticionRepository peticionRepository;

    @Mock
    private ClientesRepository clientesRepository;

    @Mock
    private EstadoPeticionRepository estadoPeticionRepository;

    @Mock
    private TipoPeticionRepository tipoPeticionRepository;
    @Mock
    private PeticionEstadoRepository peticionEstadoRepository;

    @InjectMocks
    private PeticionService peticionService;

    private PeticionDTO peticionDTO;

    private Peticion peticionExistente;
    private Peticion peticion;
    private EstadoPeticion estadoPeticion;
    private Cliente cliente;
    private TipoPeticion tipoPeticion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa los mocks correctamente
        peticionDTO = new PeticionDTO();

        peticionDTO.setIdCliente(1L);

        peticionDTO.setIdTipoPeticion(1L);

        peticionDTO.setComentarioPeticion("este es un comentario de prueba");

        peticionExistente = new Peticion();
        peticionExistente.setIdPeticion(1L);
        peticionExistente.setComentarioPeticion("Comentario anterior");

        cliente = new Cliente();
        cliente.setClienteId(1);
        cliente.setNombre("Cliente 1");
        cliente.setDireccion(null);
        cliente.setTelefono("11111111111");
        cliente.setEmail("email@email.com");


        //petcion
        peticion = new Peticion();
        peticion.setIdPeticion(1L);
        peticion.setCliente(cliente);
        peticion.setComentarioPeticion("Comentario de prueba");

        estadoPeticion = new EstadoPeticion();
        estadoPeticion.setIdEstado(1L);
        estadoPeticion.setNombreEstado("Nuevo");

        tipoPeticion = new TipoPeticion();
        tipoPeticion.setDescripcion("es una descripcion");
        tipoPeticion.setItTipoPeticion(1L);
        tipoPeticion.setDescripcionPeticion("esta es una descripcion de la descripcion");


        // Simulación de los repositorios
        when(peticionRepository.findByIdPeticion(1L)).thenReturn(peticion);
        when(estadoPeticionRepository.findById(1L)).thenReturn(Optional.of(estadoPeticion));
        when(peticionEstadoRepository.existsByPeticionIdAndPeticionEstadoIdEqualsTwo(1L)).thenReturn(false);  // No está terminado


    }

    // PRUEBAS DE CREACION DE PETICION


    @Test
    void crearPeticion_CuandoClienteNoExiste_DeberiaLanzarEntityNotFoundException() {
        // Configurar mocks para que el cliente no exista
        when(clientesRepository.findById(peticionDTO.getIdCliente().intValue())).thenReturn(Optional.empty());

        // Ejecutar y verificar la excepción
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            peticionService.crearPeticion(peticionDTO);
        });
        assertEquals("Cliente con ID " + peticionDTO.getIdCliente() + " no encontrado", exception.getMessage());
        verify(peticionRepository, never()).save(any(Peticion.class));
    }

    @Test
    void crearPeticion_CuandoEstadoPeticionNoExiste_DeberiaLanzarEntityNotFoundException() {
        // Configurar mocks para cliente existente y estado de petición inexistente
        when(clientesRepository.findById(peticionDTO.getIdCliente().intValue())).thenReturn(Optional.of(new Cliente()));
        when(estadoPeticionRepository.findById(peticionDTO.getIdTipoPeticion())).thenReturn(Optional.empty());

        // Ejecutar y verificar la excepción
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            peticionService.crearPeticion(peticionDTO);
        });
        assertEquals("EstadoPeticion con ID " + peticionDTO.getIdTipoPeticion() + " no encontrado", exception.getMessage());
        verify(peticionRepository, never()).save(any(Peticion.class));
    }

    @Test
    void crearPeticion_CuandoPeticionTieneCamposNulos_DeberiaLanzarInvalidDataException() {
        // Crear un PeticionDTO con campos nulos para probar la validación
        PeticionDTO peticionInvalida = new PeticionDTO();

        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> {
            peticionService.crearPeticion(peticionInvalida);
        });

        assertEquals("El campo 'cliente' no puede estar vacío o nulo.", exception.getMessage());
        verify(peticionRepository, never()).save(any(Peticion.class));
    }



    //PRUEBAS DE CAMBIO DE ESTADO DE PETICION



    @Test
    void testPeticionNoEncontrada() {
        // Simulación para una petición no encontrada
        when(peticionRepository.findByIdPeticion(1L)).thenReturn(null);

        // Llamada al método que debe lanzar una excepción
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            peticionService.cambioEstado(1, 1);
        });

        assertEquals("peticion no encontrada con id: 1", exception.getMessage());
    }

    @Test
    void testProyectoTerminadoNoPuedeCambiarEstado() {
        // Simulación para que la petición ya esté terminada
        when(peticionEstadoRepository.existsByPeticionIdAndPeticionEstadoIdEqualsTwo(1L)).thenReturn(true);

        // Llamada al método que debe lanzar una excepción
        ProyectoTerminadoException exception = assertThrows(ProyectoTerminadoException.class, () -> {
            peticionService.cambioEstado(1, 1);
        });

        assertEquals("La peticion esta en  fue terminado y no puede cambiar de estado", exception.getMessage());
    }



    //PRUEBAS DE ACTUALIZAR PETICION


    @Test
    void testActualizarPeticionEstadoTerminado() {
        // Simulación de peticionEstadoRepository que devuelve true (estado terminado)
        when(peticionRepository.findById(1L)).thenReturn(Optional.of(peticionExistente));
        when(peticionEstadoRepository.existsByPeticionIdAndPeticionEstadoIdEqualsTwo(1L)).thenReturn(true);

        // Intentar actualizar la petición y verificar que la excepción es lanzada
        Exception exception = assertThrows(ProyectoTerminadoException.class, () -> {
            peticionService.actualizarPeticion(1L, peticionDTO);
        });

        assertEquals("La peticion esta en estado terminada y no se puede modificar", exception.getMessage());
    }

    @Test
    void testActualizarPeticionPeticionNoExistente() {
        // Simulación de que la petición no existe
        when(peticionRepository.findById(1L)).thenReturn(Optional.empty());

        // Intentar actualizar la petición y verificar que la excepción es lanzada
        Exception exception = assertThrows(ProyectoNoEncontradoException.class, () -> {
            peticionService.actualizarPeticion(1L, peticionDTO);
        });

        assertEquals("Peticion no encontrada con id: 1", exception.getMessage());
    }

    @Test
    void testActualizarPeticionTipoPeticionNoExistente() {
        // Simulación de que el tipo de petición no existe
        when(peticionRepository.findById(1L)).thenReturn(Optional.of(peticionExistente));
        when(peticionEstadoRepository.existsByPeticionIdAndPeticionEstadoIdEqualsTwo(1L)).thenReturn(false);
        when(tipoPeticionRepository.findById(1)).thenReturn(Optional.empty());

        // Intentar actualizar la petición y verificar que la excepción es lanzada
        Exception exception = assertThrows(ProyectoNoEncontradoException.class, () -> {
            peticionService.actualizarPeticion(1L, peticionDTO);
        });

        assertEquals("Tipo de Peticion no encontrada con id: 1", exception.getMessage());
    }

    @Test
    void testActualizarPeticionClienteNoExistente() {
        // Simulación de que el cliente no existe
        when(peticionRepository.findById(1L)).thenReturn(Optional.of(peticionExistente));
        when(peticionEstadoRepository.existsByPeticionIdAndPeticionEstadoIdEqualsTwo(1L)).thenReturn(false);
        when(tipoPeticionRepository.findById(1)).thenReturn(Optional.of(new TipoPeticion()));
        when(clientesRepository.findById(1)).thenReturn(Optional.empty());

        // Intentar actualizar la petición y verificar que la excepción es lanzada
        Exception exception = assertThrows(ProyectoNoEncontradoException.class, () -> {
            peticionService.actualizarPeticion(1L, peticionDTO);
        });

        assertEquals("Cliente no encontrado con id: 1", exception.getMessage());
    }




}
