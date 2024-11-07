package gestorfreelance.gestorfreelancev5.service;

import static org.junit.jupiter.api.Assertions.*;
import gestorfreelance.gestorfreelancev5.DTO.ProyectoDTO;
import gestorfreelance.gestorfreelancev5.exception.CorreoUsuarioNoDisponibleException;
import gestorfreelance.gestorfreelancev5.exception.ProyectoTerminadoException;
import gestorfreelance.gestorfreelancev5.exception.ResourceNotFoundException;
import gestorfreelance.gestorfreelancev5.model.*;
import gestorfreelance.gestorfreelancev5.repository.*;
import gestorfreelance.gestorfreelancev5.service.ProyectoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProyectoServiceTest {

    @Mock
    private ProyectosRepository proyectoRepository;

    @Mock
    private EstadosProyectoRepository estadoProyectoRepository;

    @Mock
    private ProyectoEstadoRepository proyectoEstadoRepository;

    @Mock
    private UsuariosRepository usuariosRepository;

    @Mock
    private BolsaHorasRepository bolsaHorasRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private ProyectoService proyectoService;

    private ProyectoDTO proyectoDTO;
    private Proyecto proyecto;
    private Usuario usuario;
    private Cliente cliente;
    private EstadoProyecto estadoProyecto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicialización de datos de prueba
        cliente = new Cliente();
        cliente.setClienteId(3);
        cliente.setNombre("Cliente de prueba");
        cliente.setEmail("cliente@prueba.com");

        usuario = new Usuario();
        usuario.setUsuarioId(11);
        usuario.setNombre("Cliente de prueba");

        proyecto = new Proyecto();
        proyecto.setProyectoId(11);
        proyecto.setNombre("Proyecto de prueba");
        proyecto.setDescripcion("Descripción del proyecto");
        proyecto.setCliente(cliente);

        estadoProyecto = new EstadoProyecto();
        estadoProyecto.setEstadoId(11);

        proyectoDTO = new ProyectoDTO();
        proyectoDTO.setProyecto(proyecto);
        proyectoDTO.setHorasAsignadas(100);
    }

    @Test
    void testCrearProyecto_Success() {
        // Mocking repositorios
        when(proyectoRepository.findByNombre(proyecto.getNombre())).thenReturn(null);
        when(estadoProyectoRepository.findById(1)).thenReturn(estadoProyecto);
        when(proyectoRepository.save(proyecto)).thenReturn(proyecto);
        when(proyectoRepository.findClienteByProyectoId(proyecto.getProyectoId().longValue())).thenReturn(cliente);
        when(usuariosRepository.findMatchClient(proyecto.getProyectoId().longValue())).thenReturn(usuario);

        // Mocking Email Service
        doNothing().when(emailService).sendEmailwithAttachment(anyString(), anyString(), anyString());

        Proyecto result = proyectoService.crearProyecto(proyectoDTO);

        assertNotNull(result);
        assertEquals("Proyecto de prueba", result.getNombre());
        verify(proyectoRepository, times(1)).save(proyecto);
        verify(bolsaHorasRepository, times(1)).save(any(BolsaHora.class));
        verify(emailService, times(1)).sendEmailwithAttachment(anyString(), anyString(), anyString());
    }
    //si funciona
    @Test
    void testCrearProyecto_ProyectoExistente() {
        when(proyectoRepository.findByNombre(proyecto.getNombre())).thenReturn(proyecto);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            proyectoService.crearProyecto(proyectoDTO);
        });

        assertEquals("El proyecto ya existe y no se puede crear.", exception.getMessage());
    }

    //si funciona

    @Test
    void testObtenerProyectoPorId_Success() {
        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(proyecto));

        Proyecto result = proyectoService.obtenerProyectoPorId(1L);

        assertNotNull(result);
        assertEquals(11, result.getProyectoId());
    }
    //pasado
    @Test
    void testObtenerProyectoPorId_NotFound() {
        when(proyectoRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            proyectoService.obtenerProyectoPorId(1L);
        });

        assertEquals("Proyecto no encontrado con id: 1", exception.getMessage());
    }
    //no paso
    @Test
    void testCambioEstado_ProyectoTerminado() {
        when(proyectoRepository.findByProyectoId(1)).thenReturn(proyecto);
        when(proyectoEstadoRepository.existsByProyectoIdAndProyectoEstadoIdEqualsTwo(1L)).thenReturn(true);

        ProyectoTerminadoException exception = assertThrows(ProyectoTerminadoException.class, () -> {
            proyectoService.cambioEstado(1, 4);
        });

        assertEquals("El proyecto ya fue terminado y no puede cambiar de estado", exception.getMessage());
    }


    //si paso
    @Test
    void testEliminarProyecto_Success() {
        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(proyecto));

        proyectoService.eliminarProyecto(1L);

        verify(proyectoRepository, times(1)).delete(proyecto);
    }
    //si paso
    @Test
    void testEliminarProyecto_NotFound() {
        when(proyectoRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            proyectoService.eliminarProyecto(1L);
        });

        assertEquals("Proyecto no encontrado con id: 1", exception.getMessage());
    }
}
