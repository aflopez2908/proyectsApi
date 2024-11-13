package gestorfreelance.gestorfreelancev5.service;
import gestorfreelance.gestorfreelancev5.DTO.PeticionDTO;
import gestorfreelance.gestorfreelancev5.exception.InvalidDataException;
import gestorfreelance.gestorfreelancev5.model.*;
import gestorfreelance.gestorfreelancev5.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    @InjectMocks
    private PeticionService peticionService;

    private PeticionDTO peticionDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa los mocks correctamente
        peticionDTO = new PeticionDTO();

        peticionDTO.setIdCliente(1L);

        peticionDTO.setIdTipoPeticion(1L);

        peticionDTO.setComentarioPeticion("este es un comentario de prueba");


    }

    // PRUEBAS DE CREACION DE PETICION

    @Test
    void crearPeticion_CuandoClienteYEstadoExisten_DeberiaCrearPeticion() {
        // Configurar mocks para el cliente y el estado
        Cliente clienteMock = new Cliente();
        EstadoPeticion estadoPeticionMock = new EstadoPeticion();

        TipoPeticion tipoPeticion = new TipoPeticion();
        tipoPeticion.setItTipoPeticion(1L); // Ejemplo de ID, autogenerado en la base de datos usualmente
        tipoPeticion.setDescripcionPeticion("Solicitud de Información");
        tipoPeticion.setDescripcion("Petición para solicitar información sobre los servicios de la empresa.");

        when(clientesRepository.findById(peticionDTO.getIdCliente().intValue())).thenReturn(Optional.of(clienteMock));
        when(estadoPeticionRepository.findById(peticionDTO.getIdTipoPeticion())).thenReturn(Optional.of(estadoPeticionMock));
        when(peticionRepository.save(any(Peticion.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Ejecutar el método y verificar resultados
        Peticion resultado = peticionService.crearPeticion(peticionDTO);

        assertNotNull(resultado);
        assertEquals(clienteMock, resultado.getCliente());
        assertEquals(estadoPeticionMock, resultado.getTipoPeticion());
        assertEquals(peticionDTO.getComentarioPeticion(), resultado.getComentarioPeticion());

        verify(peticionRepository, times(1)).save(any(Peticion.class));
    }

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
}
