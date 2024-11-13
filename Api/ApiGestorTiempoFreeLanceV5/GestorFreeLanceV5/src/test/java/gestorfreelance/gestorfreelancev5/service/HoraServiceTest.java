package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.exception.CorreoUsuarioNoDisponibleException;
import gestorfreelance.gestorfreelancev5.model.*;
import gestorfreelance.gestorfreelancev5.repository.*;
import gestorfreelance.gestorfreelancev5.service.HoraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HoraServiceTest {

    @Mock
    private ProyectosRepository proyectoRepository;

    @Mock
    private EstadosProyectoRepository estadoProyectoRepository;

    @Mock
    private ProyectoEstadoRepository proyectoEstadoRepository;

    @Mock
    private BolsaHorasRepository bolsaHorasRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private ClientesRepository clientesRepository;

    @InjectMocks
    private HoraService horaService;
    private Proyecto proyecto;
    private ProyectoEstado proyectoEstado;
    private EstadoProyecto estadoProyecto;
    private BolsaHora bolsaHora;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        proyecto = new Proyecto();
        proyecto.setNombre("proyecto");
        proyecto.setDescripcion("proyecto");
        proyecto.setId(1L);

        estadoProyecto = new EstadoProyecto();
        estadoProyecto.setNombre("estado");
        estadoProyecto.setId(1L);



        proyectoEstado = new ProyectoEstado();
        proyectoEstado.setProyectoEstadoId(1L);
        proyectoEstado.setComentario("comentario");
        proyectoEstado.setProyecto(proyecto);
        proyectoEstado.setEstado(estadoProyecto);

        bolsaHora = new BolsaHora();
        bolsaHora.setProyecto(proyecto);
        bolsaHora.setHorasTotales(100);
        bolsaHora.setHorasRestantes(100);
        bolsaHora.setHorasUsadas(0);
        bolsaHora.setBolsaId(1);






    }

    @Test
    public void testAgregarHorasABolsa_Success() {
        int proyectoId = 1;
        int horas = 5;

        // Mock ProyectoEstado
        when(proyectoEstadoRepository.findByProyecto_ProyectoIdAndVigencia(proyectoId, 1))
                .thenReturn(Optional.of(proyectoEstado));
        when(proyectoEstado.getEstado().getEstadoId()).thenReturn(1);  // Estado no finalizado

        // Mock BolsaHora
        BolsaHora bolsaHora = new BolsaHora();
        bolsaHora.setHorasTotales(10);
        bolsaHora.setHorasRestantes(10);
        when(bolsaHorasRepository.findByProyecto_ProyectoId(proyectoId))
                .thenReturn(Optional.of(bolsaHora));

        String result = horaService.agregarHorasABolsa(proyectoId, horas);
        assertEquals("Horas agregadas exitosamente.", result);
        assertEquals(15, bolsaHora.getHorasTotales());
        assertEquals(15, bolsaHora.getHorasRestantes());
    }

    @Test
    public void testAgregarHorasABolsa_Error_ProyectoNoVigente() {
        int proyectoId = 1;
        int horas = 5;

        // Mock para que no se encuentre un estado vigente
        when(proyectoEstadoRepository.findByProyecto_ProyectoIdAndVigencia(proyectoId, 1))
                .thenReturn(Optional.empty());

        String result = horaService.agregarHorasABolsa(proyectoId, horas);
        assertEquals("Error: El proyecto no tiene un estado vigente.", result);
    }

    @Test
    public void testCrearBolsaHoras_Success() {
        int proyectoId = 1;
        int horasIniciales = 20;

        when(proyectoRepository.findByProyectoId(proyectoId)).thenReturn(proyecto);

        ProyectoEstado proyectoEstado = mock(ProyectoEstado.class);
        when(proyectoEstadoRepository.findByProyecto_ProyectoIdAndVigencia(proyectoId, 1))
                .thenReturn(Optional.of(proyectoEstado));
        when(proyectoEstado.getEstado().getEstadoId()).thenReturn(1);  // Estado no finalizado

        // Mock BolsaHora existente
        when(bolsaHorasRepository.findByProyecto_ProyectoId(proyectoId)).thenReturn(Optional.empty());

        // Llamada al método
        String result = horaService.crearBolsaHoras(proyectoId, horasIniciales);

        assertEquals("Nueva bolsa de horas creada exitosamente.", result);
        verify(bolsaHorasRepository, times(1)).save(any(BolsaHora.class));
    }

    @Test
    public void testCrearBolsaHoras_Error_ProyectoNoExistente() {
        int proyectoId = 1;
        int horasIniciales = 20;

        // Mock para que no se encuentre el proyecto
        when(proyectoRepository.findByProyectoId(proyectoId)).thenReturn(null);

        String result = horaService.crearBolsaHoras(proyectoId, horasIniciales);
        assertEquals("Error: El proyecto no existe.", result);
    }

    //test de consumo de horas

    @Test
    public void testConsumirHoras_Success() {
        int proyectoId = 1;
        int horasConsumidas = 5;

        // Mock BolsaHora
        BolsaHora bolsaHora = new BolsaHora();
        bolsaHora.setHorasTotales(20);
        bolsaHora.setHorasRestantes(20);
        when(bolsaHorasRepository.findByProyecto_ProyectoId(proyectoId))
                .thenReturn(Optional.of(bolsaHora));

        String result = horaService.consumirHoras(proyectoId, horasConsumidas);
        assertEquals("Horas consumidas exitosamente. Quedan 15 horas.", result);
        assertEquals(15, bolsaHora.getHorasRestantes());
    }

    @Test
    public void testConsumirHoras_Error_SinBolsa() {
        int proyectoId = 1;
        int horasConsumidas = 5;

        // Mock para que no se encuentre una bolsa de horas
        when(bolsaHorasRepository.findByProyecto_ProyectoId(proyectoId))
                .thenReturn(Optional.empty());

        String result = horaService.consumirHoras(proyectoId, horasConsumidas);
        assertEquals("Error: No se encontró una bolsa de horas asociada al proyecto.", result);
    }

    @Test
    public void testConsumirHoras_Error_SinSuficientesHoras() {
        int proyectoId = 1;
        int horasConsumidas = 25;

        // Mock BolsaHora con menos horas restantes
        BolsaHora bolsaHora = new BolsaHora();
        bolsaHora.setHorasTotales(20);
        bolsaHora.setHorasRestantes(20);
        when(bolsaHorasRepository.findByProyecto_ProyectoId(proyectoId))
                .thenReturn(Optional.of(bolsaHora));

        String result = horaService.consumirHoras(proyectoId, horasConsumidas);
        assertEquals("Error: No hay suficientes horas en la bolsa.", result);
    }

    @Test
    public void testConsumirHoras_Alerta80Porciento() throws CorreoUsuarioNoDisponibleException {
        int proyectoId = 2;
        int horasConsumidas = 90;

        when(bolsaHorasRepository.findByProyecto_ProyectoId(proyectoId))
                .thenReturn(Optional.of(bolsaHora));
        // Mock Cliente
        Cliente cliente = new Cliente();
        cliente.setEmail("cliente@ejemplo.com");
        when(clientesRepository.findClienteByProyectoId((long) proyectoId)).thenReturn(cliente);

        // Llamar al método consumirHoras
        String result = horaService.consumirHoras(proyectoId, horasConsumidas);

        // Verificación del resultado esperado
        assertEquals("Advertencia: El 80% de las horas han sido consumidas. Quedan solo 4 horas.", result);


    }

}
