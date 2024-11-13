package gestorfreelance.gestorfreelancev5.service;

import static org.junit.jupiter.api.Assertions.*;
import gestorfreelance.gestorfreelancev5.DTO.ProyectoDTO;
import gestorfreelance.gestorfreelancev5.exception.CorreoUsuarioNoDisponibleException;
import gestorfreelance.gestorfreelancev5.exception.ProyectoNoEncontradoException;
import gestorfreelance.gestorfreelancev5.exception.ProyectoTerminadoException;
import gestorfreelance.gestorfreelancev5.exception.ResourceNotFoundException;
import gestorfreelance.gestorfreelancev5.model.*;
import gestorfreelance.gestorfreelancev5.repository.*;
import gestorfreelance.gestorfreelancev5.service.ProyectoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProyectoServiceTest {

    @Mock
    private ProyectosRepository proyectoRepository;

    @Mock
    private ClientesRepository clientesRepository;



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
    private ProyectoEstado proyectoEstado;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicialización de datos de prueba
        cliente = new Cliente();
        cliente.setClienteId(1);
        cliente.setNombre("Cliente de prueba");
        cliente.setEmail("cliente@prueba.com");

        usuario = new Usuario();
        usuario.setUsuarioId(1);
        usuario.setNombre("Cliente de prueba");

        proyecto = new Proyecto();
        proyecto.setProyectoId(1);
        proyecto.setNombre("Proyecto de prueba");
        proyecto.setDescripcion("Descripción del proyecto");
        proyecto.setFechaInicio(LocalDate.now().atStartOfDay());
        proyecto.setFechaFin(LocalDate.now().plusDays(30).atStartOfDay());
        proyecto.setCliente(cliente);

        estadoProyecto = new EstadoProyecto();
        estadoProyecto.setEstadoId(1);
        estadoProyecto.setId(1L);
        estadoProyecto.setNombre("creado");

        proyectoDTO = new ProyectoDTO();
        proyectoDTO.setHorasAsignadas(100);
        proyectoDTO.setClienteId(proyecto.getCliente().getClienteId());
        proyectoDTO.setProyectoId(proyecto.getProyectoId());
        proyectoDTO.setNombre(proyecto.getNombre());
        proyectoDTO.setDescripcion(proyecto.getDescripcion());
        proyectoDTO.setFechaInicio(proyecto.getFechaInicio());
        proyectoDTO.setFechaFin(proyecto.getFechaFin());

        proyectoEstado = new ProyectoEstado();

        proyectoEstado.setProyectoEstadoId(1L);
        proyectoEstado.setProyecto(proyecto);
        proyectoEstado.setComentario("creacion");
        proyectoEstado.setEstado(estadoProyecto);
        proyectoEstado.setVigencia(1);
        proyectoEstado.setFechaCambio(Date.valueOf(LocalDate.now()));





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
    //PRUEBAS UNITARIAS DE CREACION DE PROYECTO
    @Test
    void testCrearProyecto_Exitoso() {
        // Simulamos que no existe un proyecto con el mismo nombre
        when(proyectoRepository.findByNombre(proyectoDTO.getNombre())).thenReturn(null);
        when(proyectoRepository.save(any(Proyecto.class))).thenReturn(proyecto);

        // Llamar al método de servicio para crear el proyecto
        Proyecto proyectoCreado = proyectoService.crearProyecto(proyectoDTO);
        System.out.println(proyectoCreado);
        // Verificar que el proyecto fue guardado correctamente
        assertNotNull(proyectoCreado);
        //assertEquals(proyecto.getNombre(), proyectoCreado.getNombre());
        //assertEquals(proyecto.getDescripcion(), proyectoCreado.getDescripcion());
        //assertEquals(proyecto.getCliente(), proyectoCreado.getCliente());
        //assertEquals(proyecto.getFechaInicio(), proyectoCreado.getFechaInicio());
        //assertEquals(proyecto.getFechaFin(), proyectoCreado.getFechaFin());

        // Verificar que el repositorio fue llamado para guardar el proyecto
        //verify(proyectoRepository, times(1)).save(any(Proyecto.class));
    }

    @Test
    void testCrearProyecto_ConNombreExistente() {
        // Simulamos que ya existe un proyecto con el mismo nombre
        when(proyectoRepository.findByNombre(proyectoDTO.getNombre())).thenReturn(proyecto);

        // Llamar al método de servicio para crear el proyecto
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            proyectoService.crearProyecto(proyectoDTO);
        });

        // Verificar el mensaje de error
        assertEquals("Ya existe un proyecto con el mismo nombre", exception.getMessage());
    }

    @Test
    void testCrearProyecto_FaltanCamposObligatorios() {
        // Dejamos el nombre en null para simular que falta un campo obligatorio
        proyecto.setNombre(null);

        // Llamar al método de servicio para crear el proyecto
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            proyectoService.crearProyecto(proyectoDTO);
        });

        // Verificar que se lanza la excepción por el campo faltante
        assertEquals("El nombre del proyecto no puede ser nulo o vacío", exception.getMessage());
    }

    @Test
    void testCrearProyecto_FechaInicioPosteriorAFin() {
        // Establecemos una fecha de inicio posterior a la fecha de fin
        proyecto.setFechaInicio(LocalDate.now().plusDays(30).atStartOfDay());
        proyecto.setFechaFin(LocalDate.now().plusDays(20).atStartOfDay());

        // Llamar al método de servicio para crear el proyecto
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            proyectoService.crearProyecto(proyectoDTO);
        });

        // Verificar que se lanza la excepción debido a la fecha inválida
        assertEquals("La fecha de inicio no puede ser posterior a la fecha de fin", exception.getMessage());
    }

    @Test
    void testCrearProyecto_ErrorAlGuardar() {
        // Simulamos que no existe un proyecto con el mismo nombre
        when(proyectoRepository.findByNombre(proyecto.getNombre())).thenReturn(null);

        // Simulamos un error al guardar el proyecto
        when(proyectoRepository.save(any(Proyecto.class))).thenThrow(new DataIntegrityViolationException("Error al guardar el proyecto"));

        // Llamar al método de servicio para crear el proyecto
        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            proyectoService.crearProyecto(proyectoDTO);
        });

        // Verificar que se lanza la excepción de error al guardar el proyecto
        assertEquals("Error al guardar el proyecto", exception.getMessage());
    }








    // pruebas unitarias correspondientes a la logica del negocio de la eliminacion de proyectos
    //pasado
    @Test
    void testEliminarProyecto_Exitoso() {
        // Simular que el proyecto existe en la base de datos
        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(proyecto));
        doNothing().when(proyectoRepository).eliminarRelacionesPorProyectoId(1L);
        doNothing().when(proyectoRepository).delete(proyecto);

        // Llamar al método de servicio para eliminar el proyecto
        proyectoService.eliminarProyecto(1L);

        // Verificar que los métodos correctos fueron llamados en el repositorio
        verify(proyectoRepository, times(1)).findById(1L);
        verify(proyectoRepository, times(1)).eliminarRelacionesPorProyectoId(1L);
        verify(proyectoRepository, times(1)).delete(proyecto);
    }
    //no pasado
    @Test
    void testEliminarProyecto_NoEncontrado() {
        // Simular que no se encuentra el proyecto
        when(proyectoRepository.findById(1L)).thenReturn(Optional.empty());

        // Probar que se lanza una excepción personalizada cuando el proyecto no se encuentra
        Exception exception = assertThrows(ProyectoNoEncontradoException.class, () -> {
            proyectoService.eliminarProyecto(1L);
        });

        // Verificar que el mensaje de la excepción es correcto
        assertEquals("No se encontró el proyecto con ID: 1", exception.getMessage());
    }
    @Test
    void testEliminarProyecto_ErrorEnRelaciones() {
        // Simular que el proyecto existe
        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(proyecto));

        // Simular que ocurre un error al eliminar las relaciones
        doThrow(new RuntimeException("Error al eliminar relaciones")).when(proyectoRepository).eliminarRelacionesPorProyectoId(1L);

        // Probar que se lanza una excepción al intentar eliminar el proyecto
        Exception exception = assertThrows(RuntimeException.class, () -> {
            proyectoService.eliminarProyecto(1L);
        });

        // Verificar que el mensaje de la excepción es el esperado
        assertEquals("Error al eliminar relaciones", exception.getMessage());
    }
    @Test
    void testEliminarProyecto_SiElProyectoNoSeElimina() {
        // Simular que el proyecto existe
        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(proyecto));

        // Simular que el proyecto no se elimina
        doThrow(new EmptyResultDataAccessException(1)).when(proyectoRepository).delete(proyecto);

        // Probar que se lanza una excepción al intentar eliminar el proyecto
        Exception exception = assertThrows(EmptyResultDataAccessException.class, () -> {
            proyectoService.eliminarProyecto(1L);
        });

        // Verificar que la excepción se lanza correctamente
        assertNotNull(exception);
    }



    //PRUEBAS UNITARIAS PARA CAMBIAR EL ESTADO DE UN PROYECTO



    @Test
    void testCambiarEstado_Exitoso() {

        // Crear el estado anterior (actual) del proyecto
        EstadoProyecto estadoCambiado = new EstadoProyecto();
        estadoCambiado.setId(2L);
        estadoCambiado.setNombre("progreso");

        // Crear el estado nuevo que se asignará al proyecto
        ProyectoEstado estadonNuevo = new ProyectoEstado();

        estadonNuevo.setProyecto(proyecto);
        estadonNuevo.setComentario("cambio");
        estadonNuevo.setEstado(estadoCambiado);
        estadonNuevo.setVigencia(1);
        estadonNuevo.setFechaCambio(Date.valueOf(LocalDate.now()));


        // Simular que el proyecto existe en la base de datos
        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(proyecto));

        // Llamar al método para cambiar el estado
        ProyectoEstado proyectoActualizado = proyectoService.cambioEstado(1, 2);


        // Verificar que el estado se ha actualizado correctamente
        assertNotNull(proyectoActualizado);
        assertEquals(estadonNuevo, proyectoEstado.getEstado());

        // Verificar que el repositorio ha sido llamado para guardar el proyecto actualizado
        verify(proyectoRepository, times(1)).save(proyecto);
    }










}
