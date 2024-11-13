package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.model.BolsaHora;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.ProyectoEstado;
import gestorfreelance.gestorfreelancev5.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HoraService {
    @Autowired
    private ProyectosRepository proyectoRepository;

    @Autowired
    private EstadosProyectoRepository estadoProyectoRepository;

    @Autowired
    private ProyectoEstadoRepository proyectoEstadoRepository;

    @Autowired
    private BolsaHorasRepository bolsaHorasRepository;
    @Autowired
    private HistorialTareasRepository historialTareasRepository;
    @Autowired
    private TareasRepository tareasRepository;

    public String agregarHorasABolsa(int proyectoId, int horas) {
        Optional<ProyectoEstado> proyectoEstadoOpt = proyectoEstadoRepository
                .findByProyecto_ProyectoIdAndVigencia(proyectoId, 1);
        if (proyectoEstadoOpt.isEmpty()) {
            return "Error: El proyecto no tiene un estado vigente.";
        }
        ProyectoEstado proyectoEstado = proyectoEstadoOpt.get();
        if (proyectoEstado.getEstado().getEstadoId() == 4) {
            return "Error: No se pueden agregar horas, el proyecto está finalizado.";
        }
        Optional<BolsaHora> bolsaHoraOpt = bolsaHorasRepository.findByProyecto_ProyectoId(proyectoId);

        if (bolsaHoraOpt.isPresent()) {
            BolsaHora bolsaHora = bolsaHoraOpt.get();
            int horasTotalesActuales = bolsaHora.getHorasTotales();
            int horasRestantesActuales = bolsaHora.getHorasRestantes();
            bolsaHora.setHorasTotales(horasTotalesActuales + horas);
            bolsaHora.setHorasRestantes(horasRestantesActuales + horas);
            bolsaHorasRepository.save(bolsaHora);
            return "Horas agregadas exitosamente.";
        } else {
            return "Error: No se encontró una bolsa de horas asociada al proyecto.";
        }
    }

    public String crearBolsaHoras(int proyectoId, int horasIniciales) {
        Proyecto proyecto = proyectoRepository.findByProyectoId(proyectoId);
        if (proyecto == null) {
            return "Error: El proyecto no existe.";
        }
        Optional<ProyectoEstado> proyectoEstadoOpt = proyectoEstadoRepository
                .findByProyecto_ProyectoIdAndVigencia(proyectoId, 1);
        if (proyectoEstadoOpt.isEmpty()) {
            return "Error: El proyecto no tiene un estado vigente.";
        }
        ProyectoEstado proyectoEstado = proyectoEstadoOpt.get();
        if (proyectoEstado.getEstado().getEstadoId() == 4) {
            return "Error: No se puede crear una bolsa de horas, el proyecto está finalizado.";
        }
        Optional<BolsaHora> bolsaExistente = bolsaHorasRepository.findByProyecto_ProyectoId(proyectoId);
        if (bolsaExistente.isPresent()) {
            System.out.println("Horas Existentes" + bolsaExistente);
            if (bolsaExistente.get().getHorasRestantes() != 0)
                return "Error: Ya existe una bolsa de horas para este proyecto.";
        }

        BolsaHora nuevaBolsa = BolsaHora.builder()
                .proyecto(proyecto)
                .horasTotales(horasIniciales)
                .horasUsadas(0)
                .horasRestantes(horasIniciales)
                .build();
        bolsaHorasRepository.save(nuevaBolsa);

        return "Nueva bolsa de horas creada exitosamente.";
    }

    public int getTotalHorasPorUsuario(Long usuarioId) {
        List<BolsaHora> bolsasHoras = bolsaHorasRepository.findByProyecto_Tarea_Historial_UsuarioId(usuarioId);
        return bolsasHoras.stream()
                .mapToInt(BolsaHora::getHorasUsadas)
                .sum();
    }

    public int getTotalHorasUsadasPorProyecto(Long proyectoId) {
        BolsaHora bolsaHora = bolsaHorasRepository.findByProyecto_ProyectoId(proyectoId.intValue())
                .orElseThrow(() -> new RuntimeException("El proyecto con ID " + proyectoId + " no tiene una bolsa de horas asignada."));
        return bolsaHora.getHorasUsadas();
    }

    public int getHorasRestantesPorProyecto(Long proyectoId) {
        BolsaHora bolsaHora = bolsaHorasRepository.findByProyecto_ProyectoId(proyectoId.intValue())
                .orElseThrow(() -> new RuntimeException("El proyecto con ID " + proyectoId + " no tiene una bolsa de horas asignada."));
        return bolsaHora.getHorasRestantes();
    }

    public int getHorasPorUsuarioYRangoDeFechas(Long usuarioId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<BolsaHora> bolsasHoras = bolsaHorasRepository.findByProyecto_Tarea_Historial_UsuarioIdAndFechaBetween(usuarioId, fechaInicio, fechaFin);
        return bolsasHoras.stream()
                .mapToInt(BolsaHora::getHorasUsadas)
                .sum();
    }

    public Map<String, Object> getEstadisticasGlobales() {
        Map<String, Object> estadisticasGlobales = new HashMap<>();

        int totalHorasUsadas = bolsaHorasRepository.findAll().stream()
                .mapToInt(BolsaHora::getHorasUsadas)
                .sum();

        int totalHorasRestantes = bolsaHorasRepository.findAll().stream()
                .mapToInt(BolsaHora::getHorasRestantes)
                .sum();

        int totalHorasTrabajadas = totalHorasUsadas;
        long totalProyectos = proyectoRepository.count();
        long totalTareas = tareasRepository.count();

        estadisticasGlobales.put("totalHorasUsadas", totalHorasUsadas);
        estadisticasGlobales.put("totalHorasRestantes", totalHorasRestantes);
        estadisticasGlobales.put("totalHorasTrabajadas", totalHorasTrabajadas);
        estadisticasGlobales.put("totalProyectos", totalProyectos);
        estadisticasGlobales.put("totalTareas", totalTareas);

        return estadisticasGlobales;
    }

}
