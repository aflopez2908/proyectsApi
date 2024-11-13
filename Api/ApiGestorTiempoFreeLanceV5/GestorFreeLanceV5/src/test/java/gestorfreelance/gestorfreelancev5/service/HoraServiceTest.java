package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.model.BolsaHora;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.ProyectoEstado;
import gestorfreelance.gestorfreelancev5.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HoraServiceTest {

    @InjectMocks
    private HoraService horaService;

    @Mock
    private ProyectosRepository proyectoRepository;

    @Mock
    private BolsaHorasRepository bolsaHorasRepository;

    @Mock
    private ProyectoEstadoRepository proyectoEstadoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAgregarHorasABolsa() {
        ProyectoEstado proyectoEstado = new ProyectoEstado();
        ReflectionTestUtils.setField(proyectoEstado, "estadoId", 1); // Asignar el valor al campo privado

        BolsaHora bolsaHora = new BolsaHora();
        bolsaHora.setHorasTotales(10);
        bolsaHora.setHorasRestantes(5);

        when(proyectoEstadoRepository.findByProyecto_ProyectoIdAndVigencia(1, 1))
                .thenReturn(Optional.of(proyectoEstado));
        when(bolsaHorasRepository.findByProyecto_ProyectoId(1))
                .thenReturn(Optional.of(bolsaHora));

        String resultado = horaService.agregarHorasABolsa(1, 5);

        assertEquals("Horas agregadas exitosamente.", resultado);
        verify(bolsaHorasRepository, times(1)).save(bolsaHora);
    }

    @Test
    void testCrearBolsaHoras() {
        Proyecto proyecto = new Proyecto();
        proyecto.setProyectoId(1);

        ProyectoEstado proyectoEstado = new ProyectoEstado();
        ReflectionTestUtils.setField(proyectoEstado, "estadoId", 1); // Asigna el valor al campo privado

        when(proyectoRepository.findByProyectoId(1)).thenReturn(proyecto);
        when(proyectoEstadoRepository.findByProyecto_ProyectoIdAndVigencia(1, 1))
                .thenReturn(Optional.of(proyectoEstado));

        String resultado = horaService.crearBolsaHoras(1, 10);

        assertEquals("Nueva bolsa de horas creada exitosamente.", resultado);
        verify(bolsaHorasRepository, times(1)).save(any(BolsaHora.class));
    }
}