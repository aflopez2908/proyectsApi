package gestorfreelance.gestorfreelancev5.service;

import static org.junit.jupiter.api.Assertions.*;
import gestorfreelance.gestorfreelancev5.DTO.ProyectoDTO;
import gestorfreelance.gestorfreelancev5.exception.*;
import gestorfreelance.gestorfreelancev5.model.*;
import gestorfreelance.gestorfreelancev5.repository.*;
import gestorfreelance.gestorfreelancev5.service.ProyectoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.util.ReflectionTestUtils;

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
    private Proyecto proyectoExistente;
    private Proyecto detallesProyecto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicialización de datos de prueba

        usuario = new Usuario();
        usuario.setUsuarioId(1);
        usuario.setNombre("Cliente de prueba");


        estadoProyecto = new EstadoProyecto();
        estadoProyecto.setEstadoId(1);
        estadoProyecto.setId(1L);
        estadoProyecto.setNombre("creado");


        proyectoEstado = new ProyectoEstado();

        proyectoEstado.setProyectoEstadoId(1L);
        proyectoEstado.setProyecto(proyecto);
        proyectoEstado.setComentario("creacion");
        proyectoEstado.setEstado(estadoProyecto);
        proyectoEstado.setVigencia(1);
        proyectoEstado.setFechaCambio(Date.valueOf(LocalDate.now()));

        proyectoExistente = new Proyecto();
        proyectoExistente.setProyectoId(1);
        proyectoExistente.setNombre("Proyecto Inicial");
        proyectoExistente.setDescripcion("Descripción Inicial");

        detallesProyecto = new Proyecto();
        detallesProyecto.setNombre("Proyecto Actualizado");
        detallesProyecto.setDescripcion("Descripción Actualizada");

        estadoProyecto = new EstadoProyecto();
        estadoProyecto.setEstadoId(1); // Por ejemplo, estado 1 para 'En Progreso'

        cliente = new Cliente();
        cliente.setClienteId(1);
        cliente.setNombre("Cliente Prueba");
        cliente.setEmail("cliente@prueba.com");
        cliente.setTelefono("111111111");


        proyectoDTO = new ProyectoDTO();
        proyectoDTO.setNombre("Nuevo Proyecto");
        proyectoDTO.setDescripcion("Descripción del proyecto");
        proyectoDTO.setClienteId(cliente.getClienteId());
        proyectoDTO.setFechaInicio(LocalDate.now().atStartOfDay());
        proyectoDTO.setFechaFin(LocalDate.now().plusDays(10).atStartOfDay());
        proyectoDTO.setHorasAsignadas(100);

        proyecto = new Proyecto();
        proyecto.setProyectoId(1);
        proyecto.setNombre("Nuevo Proyecto");
        proyecto.setDescripcion("Descripción del proyecto");
        proyecto.setCliente(cliente);
        proyecto.setFechaInicio(LocalDate.now().atStartOfDay());
        proyecto.setFechaFin(LocalDate.now().plusDays(10).atStartOfDay());






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
    void crearProyecto_FechaFinMenorAFechaInicio_LanzaExcepcion() {
        // Ajustar la fecha de fin para que sea antes de la fecha de inicio
        proyectoDTO.setFechaFin(LocalDate.now().minusDays(1).atStartOfDay());

        // Ejecutar el servicio y verificar la excepción
        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> {
            proyectoService.crearProyecto(proyectoDTO);
        });

        assertEquals("La fecha de fin no puede ser menor a la de inicio", exception.getMessage());
    }

    @Test
    void crearProyecto_ProyectoExistente_LanzaExcepcion() {
        System.out.println(proyecto.getCliente().getClienteId());
        // Configurar mocks para que se simule un proyecto existente
        when(proyectoRepository.findByNombre(proyecto.getNombre())).thenReturn(proyecto);

        // Ejecutar el servicio y verificar la excepción
        ProyectoExistenteException exception = assertThrows(ProyectoExistenteException.class, () -> {
            proyectoService.crearProyecto(proyectoDTO);
        });

        assertEquals("El proyecto ya existe y no se puede crear.", exception.getMessage());
    }

    @Test
    void crearProyecto_ProyectoValido_CreadoCorrectamente() {
        // Configurar los mocks
        when(proyectoRepository.findByNombre(proyectoDTO.getNombre())).thenReturn(null); // No existe el proyecto
        when(clientesRepository.findByClienteId(proyectoDTO.getClienteId())).thenReturn(cliente);
        when(proyectoRepository.save(any(Proyecto.class))).thenReturn(proyecto); // Guardar proyecto
        when(estadoProyectoRepository.findById(1)).thenReturn(estadoProyecto); // Estado de proyecto

        // Ejecutar el servicio
        Proyecto proyectoCreado = proyectoService.crearProyecto(proyectoDTO);

        // Verificar que el proyecto fue creado correctamente
        assertNotNull(proyectoCreado);
        assertEquals("Nuevo Proyecto", proyectoCreado.getNombre());
        assertEquals("Descripción del proyecto", proyectoCreado.getDescripcion());

        // Verificar interacciones con los mocks
        verify(proyectoRepository).save(any(Proyecto.class));
        verify(bolsaHorasRepository).save(any(BolsaHora.class));
        verify(estadoProyectoRepository).findById(1);
    }

    @Test
    void crearProyecto_ClienteNoExistente_LanzaExcepcion() {
        // Configurar el mock para devolver Optional.empty()
        when(clientesRepository.findById(proyectoDTO.getClienteId())).thenReturn(Optional.empty());

        // Ejecutar el servicio y verificar la excepción
        ClienteNoEncontradoException exception = assertThrows(ClienteNoEncontradoException.class, () -> {
            proyectoService.crearProyecto(proyectoDTO);
        });

        assertEquals("Cliente no encontrado", exception.getMessage());
    }

    @Test
    void crearProyecto_CreacionBolsaHora_Exitosa() {
        // Configurar los mocks para la creación de proyecto y cliente
        when(proyectoRepository.findByNombre(proyectoDTO.getNombre())).thenReturn(null);
        when(clientesRepository.findByClienteId(proyectoDTO.getClienteId())).thenReturn(cliente);
        when(proyectoRepository.save(any(Proyecto.class))).thenReturn(proyecto);
        when(bolsaHorasRepository.save(any(BolsaHora.class))).thenReturn(new BolsaHora());

        // Ejecutar el servicio
        Proyecto proyectoCreado = proyectoService.crearProyecto(proyectoDTO);

        // Verificar que se haya guardado la BolsaHora
        verify(bolsaHorasRepository).save(any(BolsaHora.class));
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
    void cambioEstado_proyectoNoEncontrado_throwsResourceNotFoundException() {
        when(proyectoRepository.findByProyectoId(1)).thenReturn(null);

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> proyectoService.cambioEstado(1, 1)
        );

        assertEquals("Proyecto no encontrado con id: 1", exception.getMessage());
    }

    @Test
    void cambioEstado_estadoNoValido_throwsIllegalArgumentException() {
        Proyecto proyecto = new Proyecto();
        proyecto.setProyectoId(1);
        when(proyectoRepository.findByProyectoId(1)).thenReturn(proyecto);
        when(estadoProyectoRepository.findById(1)).thenReturn(null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> proyectoService.cambioEstado(1, 1)
        );

        assertEquals("El estado proporcionado no es válido. Por favor, elija un estado correcto.", exception.getMessage());
    }

    @Test
    void cambioEstado_proyectoTerminado_throwsProyectoTerminadoException() {
        Proyecto proyecto = new Proyecto();
        proyecto.setProyectoId(1);
        EstadoProyecto estadoProyecto = new EstadoProyecto();
        estadoProyecto.setEstadoId(1);

        when(proyectoRepository.findByProyectoId(1)).thenReturn(proyecto);
        when(estadoProyectoRepository.findById(1)).thenReturn(estadoProyecto);
        when(proyectoEstadoRepository.existsByProyectoIdAndProyectoEstadoIdEqualsTwo(1L)).thenReturn(true);

        ProyectoTerminadoException exception = assertThrows(
                ProyectoTerminadoException.class,
                () -> proyectoService.cambioEstado(1, 1)
        );

        assertEquals("El proyecto ya fue terminado y no puede cambiar de estado", exception.getMessage());
    }

    @Test
    void cambioEstado_estadoInvalidoParaCreacion_throwsIllegalArgumentException() {
        Proyecto proyecto = new Proyecto();
        proyecto.setProyectoId(1);
        EstadoProyecto estadoProyecto = new EstadoProyecto();
        estadoProyecto.setEstadoId(2);

        when(proyectoRepository.findByProyectoId(1)).thenReturn(proyecto);
        when(estadoProyectoRepository.findById(2)).thenReturn(estadoProyecto);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> proyectoService.cambioEstado(1, 1)
        );

        assertEquals("El estado proporcionado es de cracion y el proyecto ya fue creado", exception.getMessage());
    }

    @Test
    void cambioEstado_estadoCambiado_exitosamente() {
        Proyecto proyecto = new Proyecto();
        proyecto.setProyectoId(1);
        EstadoProyecto estadoProyecto = new EstadoProyecto();
        estadoProyecto.setEstadoId(3);
        ProyectoEstado proyectoEstado = new ProyectoEstado();

        when(proyectoRepository.findByProyectoId(1)).thenReturn(proyecto);
        when(estadoProyectoRepository.findById(3)).thenReturn(estadoProyecto);
        when(proyectoEstadoRepository.existsByProyectoIdAndProyectoEstadoIdEqualsTwo(1L)).thenReturn(false);
        when(proyectoEstadoRepository.findByProyectoId(1L)).thenReturn(proyectoEstado);

        ProyectoEstado result = proyectoService.cambioEstado(1, 3);

        assertNotNull(result);
        verify(proyectoEstadoRepository).actualizarVigencia(1L);

        // Usando ReflectionTestUtils para invocar el método privado registrarEstado
        ReflectionTestUtils.invokeMethod(proyectoService, "registrarEstado", proyecto, estadoProyecto, "actualizacion", 1);
    }






    //PRUEBAS CORRESPONDIENTES A ACTUALIZAR





    @Test
    void actualizarProyecto_ProyectoExistente_Actualizado() {
        // Configuración de mocks
        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(proyectoExistente));
        when(proyectoEstadoRepository.existsByProyectoIdAndProyectoEstadoIdEqualsTwo(1L)).thenReturn(false); // Proyecto no terminado
        when(proyectoRepository.save(proyectoExistente)).thenReturn(proyectoExistente);

        // Ejecutar el servicio
        Proyecto proyectoActualizado = proyectoService.actualizarProyecto(1L, detallesProyecto);

        // Verificar el resultado
        assertNotNull(proyectoActualizado);
        assertEquals("Proyecto Actualizado", proyectoActualizado.getNombre());
        assertEquals("Descripción Actualizada", proyectoActualizado.getDescripcion());

        // Verificar interacciones con mocks
        verify(proyectoRepository).findById(1L);
        verify(proyectoRepository).save(proyectoExistente);
    }

    @Test
    void actualizarProyecto_ProyectoNoEncontrado_LanzaExcepcion() {
        // Configuración de mocks
        when(proyectoRepository.findById(1L)).thenReturn(Optional.empty());

        // Ejecutar el servicio y verificar la excepción
        ProyectoNoEncontradoException exception = assertThrows(ProyectoNoEncontradoException.class, () -> {
            proyectoService.actualizarProyecto(1L, detallesProyecto);
        });

        assertEquals("proyecto no encontrado con el id:1", exception.getMessage());
    }

    @Test
    void actualizarProyecto_ProyectoTerminado_LanzaExcepcion() {
        // Configuración de mocks
        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(proyectoExistente));
        when(proyectoEstadoRepository.existsByProyectoIdAndProyectoEstadoIdEqualsTwo(1L)).thenReturn(true); // Proyecto ya terminado

        // Ejecutar el servicio y verificar la excepción
        ProyectoTerminadoException exception = assertThrows(ProyectoTerminadoException.class, () -> {
            proyectoService.actualizarProyecto(1L, detallesProyecto);
        });

        assertEquals("El proyecto ya esta terminado no se puede cambiar el estado", exception.getMessage());
    }



    @Test
    void actualizarProyecto_NombreDuplicado_LanzaExcepcion() {
        // Configuración de mocks
        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(proyectoExistente));
        when(proyectoEstadoRepository.existsByProyectoIdAndProyectoEstadoIdEqualsTwo(1L)).thenReturn(false);
        when(proyectoRepository.findByNombre(detallesProyecto.getNombre())).thenReturn(proyectoExistente); // Nombre duplicado

        // Ejecutar el servicio y verificar la excepción
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            proyectoService.actualizarProyecto(1L, detallesProyecto);
        });

        assertEquals("El proyecto ya existe y no se puede crear.", exception.getMessage());
    }

    @Test
    void actualizarProyecto_ValidacionCampos_LanzaExcepcion() {
        // Detalles del proyecto con nombre vacío
        detallesProyecto.setNombre(null); // Nombre nulo

        // Ejecutar el servicio y verificar la excepción
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            proyectoService.actualizarProyecto(1L, detallesProyecto);
        });

        assertEquals("El campo nombre no puede ser nulo o vacío", exception.getMessage());
    }












}
