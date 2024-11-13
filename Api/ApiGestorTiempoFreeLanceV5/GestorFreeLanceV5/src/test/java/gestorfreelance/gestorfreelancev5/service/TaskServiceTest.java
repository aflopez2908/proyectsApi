package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.exception.ProyectoNotFoundException;
import gestorfreelance.gestorfreelancev5.model.*;
import gestorfreelance.gestorfreelancev5.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TareasRepository tareasRepository;

    @Mock
    private ProyectosRepository proyectosRepository;

    @Mock
    private BolsaHorasRepository bolsaHorasRepository;

    @Mock
    private HistorialTareasRepository historialTareasRepository;

    @Mock
    private UsuariosRepository usuariosRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTarea_ProyectoNoExistente() {
        Tarea tarea = new Tarea();
        Proyecto proyecto = new Proyecto();
        proyecto.setProyectoId(1);
        tarea.setProyecto(proyecto);

        when(proyectosRepository.findByProyectoId(1)).thenReturn(null);

        ProyectoNotFoundException exception = assertThrows(
                ProyectoNotFoundException.class,
                () -> taskService.createTarea(tarea)
        );

        assertEquals("El proyecto con ID 1 no existe", exception.getMessage());
    }

    @Test
    void testCreateTarea_SinHorasDisponibles() {
        Tarea tarea = new Tarea();
        Proyecto proyecto = new Proyecto();
        proyecto.setProyectoId(1);
        tarea.setProyecto(proyecto);

        Proyecto proyectoExistente = new Proyecto();
        BolsaHora bolsaHora = new BolsaHora();
        bolsaHora.setHorasRestantes(0);

        when(proyectosRepository.findByProyectoId(1)).thenReturn(proyectoExistente);
        when(bolsaHorasRepository.findByProyecto_ProyectoId(1)).thenReturn(Optional.of(bolsaHora));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> taskService.createTarea(tarea)
        );

        assertEquals("El proyecto con ID 1 no cuenta con horas suficientes para la creación de tareas.", exception.getMessage());
    }
}
