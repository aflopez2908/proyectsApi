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

    public String agregarHorasABolsa(Long proyectoId, int horas) {
        // Verificar si el proyecto existe y tiene un estado vigente que no sea finalizado
        Optional<ProyectoEstado> proyectoEstadoOpt = proyectoEstadoRepository
                .findByProyectoIdAndVigencia(proyectoId, 1); // Buscar el estado vigente

        if (proyectoEstadoOpt.isEmpty()) {
            return "Error: El proyecto no tiene un estado vigente.";
        }

        ProyectoEstado proyectoEstado = proyectoEstadoOpt.get();
        if (proyectoEstado.getEstado().getEstadoId() == 4) {
            return "Error: No se pueden agregar horas, el proyecto está finalizado.";
        }

        // Buscar la bolsa de horas asociada al proyecto
        Optional<BolsaHora> bolsaHoraOpt = bolsaHorasRepository.findByProyectoId(proyectoId);

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

}
