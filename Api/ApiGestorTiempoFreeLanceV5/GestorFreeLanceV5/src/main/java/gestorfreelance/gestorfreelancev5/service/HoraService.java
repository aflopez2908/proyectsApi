package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.model.BolsaHora;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.ProyectoEstado;
import gestorfreelance.gestorfreelancev5.repository.BolsaHorasRepository;
import gestorfreelance.gestorfreelancev5.repository.EstadosProyectoRepository;
import gestorfreelance.gestorfreelancev5.repository.ProyectoEstadoRepository;
import gestorfreelance.gestorfreelancev5.repository.ProyectosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String agregarHorasABolsa(int proyectoId, int horas) {
        // Verificar si el proyecto existe y tiene un estado vigente que no sea finalizado
        Optional<ProyectoEstado> proyectoEstadoOpt = proyectoEstadoRepository
                .findByProyecto_ProyectoIdAndVigencia(proyectoId, 1); // Buscar el estado vigente

        if (proyectoEstadoOpt.isEmpty()) {
            return "Error: El proyecto no tiene un estado vigente.";
        }

        ProyectoEstado proyectoEstado = proyectoEstadoOpt.get();
        if (proyectoEstado.getEstado().getEstadoId() == 4) {
            return "Error: No se pueden agregar horas, el proyecto está finalizado.";
        }

        // Buscar la bolsa de horas asociada al proyecto
        Optional<BolsaHora> bolsaHoraOpt = bolsaHorasRepository.findByProyecto_ProyectoId(proyectoId);

        if (bolsaHoraOpt.isPresent()) {
            BolsaHora bolsaHora = bolsaHoraOpt.get();
            int horasTotalesActuales = bolsaHora.getHorasTotales();
            int horasRestantesActuales = bolsaHora.getHorasRestantes();

            // Actualizar las horas totales y restantes
            bolsaHora.setHorasTotales(horasTotalesActuales + horas);
            bolsaHora.setHorasRestantes(horasRestantesActuales + horas);

            // Guardar los cambios en la base de datos
            bolsaHorasRepository.save(bolsaHora);

            return "Horas agregadas exitosamente.";
        } else {
            return "Error: No se encontró una bolsa de horas asociada al proyecto.";
        }
    }
    public String crearBolsaHoras(int proyectoId, int horasIniciales) {
        // Verificar si el proyecto existe
        Proyecto proyecto = proyectoRepository.findByProyectoId(proyectoId);
        if (proyecto == null) {
            return "Error: El proyecto no existe.";
        }

       // Proyecto proyecto = proyectoOpt;

        // Verificar si el proyecto existe y tiene un estado vigente que no sea finalizado
        Optional<ProyectoEstado> proyectoEstadoOpt = proyectoEstadoRepository
                .findByProyecto_ProyectoIdAndVigencia(proyectoId, 1); // Buscar el estado vigente

        if (proyectoEstadoOpt.isEmpty()) {
            return "Error: El proyecto no tiene un estado vigente.";
        }

        ProyectoEstado proyectoEstado = proyectoEstadoOpt.get();
        if (proyectoEstado.getEstado().getEstadoId() == 4) {
            return "Error: No se puede crear una bolsa de horas, el proyecto está finalizado.";
        }

        // Verificar si ya existe una bolsa de horas para el proyecto
        Optional<BolsaHora> bolsaExistente = bolsaHorasRepository.findByProyecto_ProyectoId(proyectoId);
        if (bolsaExistente.isPresent()) {
            System.out.println("Horas Existentes" + bolsaExistente);
            if (bolsaExistente.get().getHorasRestantes() != 0)
                return "Error: Ya existe una bolsa de horas para este proyecto.";
        }

        // Crear una nueva bolsa de horas
        BolsaHora nuevaBolsa = BolsaHora.builder()
                .proyecto(proyecto)
                .horasTotales(horasIniciales)
                .horasUsadas(0)
                .horasRestantes(horasIniciales)
                .build();

        // Guardar la nueva bolsa de horas en la base de datos
        bolsaHorasRepository.save(nuevaBolsa);

        return "Nueva bolsa de horas creada exitosamente.";
    }
}
