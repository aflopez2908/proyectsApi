package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.exception.CorreoUsuarioNoDisponibleException;
import gestorfreelance.gestorfreelancev5.model.BolsaHora;
import gestorfreelance.gestorfreelancev5.model.Cliente;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.ProyectoEstado;
import gestorfreelance.gestorfreelancev5.repository.*;
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

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClientesRepository clientesRepository;



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


    // Método para consumir horas
    public String consumirHoras(int proyectoId, int horasConsumidas) {
        Optional<BolsaHora> bolsaHoraOpt = bolsaHorasRepository.findByProyecto_ProyectoId(proyectoId);

        if (bolsaHoraOpt.isEmpty()) {
            return "Error: No se encontró una bolsa de horas asociada al proyecto.";
        }

        BolsaHora bolsaHora = bolsaHoraOpt.get();
        int horasRestantes = bolsaHora.getHorasRestantes();

        if (horasConsumidas > horasRestantes) {
            return "Error: No hay suficientes horas en la bolsa.";
        }

        // Actualizar las horas restantes y las horas usadas
        bolsaHora.setHorasRestantes(horasRestantes - horasConsumidas);
        bolsaHora.setHorasUsadas(bolsaHora.getHorasUsadas() + horasConsumidas);
        bolsaHorasRepository.save(bolsaHora);

        // Verificar si se ha alcanzado el 80% de consumo de las horas
        double porcentajeConsumido = (double) bolsaHora.getHorasUsadas() / bolsaHora.getHorasTotales() * 100;
        if (porcentajeConsumido >= 80) {

            Cliente cliente = clientesRepository.findClienteByProyectoId((long) proyectoId);
            Proyecto proyecto = proyectoRepository.findByProyectoId(proyectoId);
            if (proyecto == null) {
                throw new RuntimeException("Error: El proyecto no existe.");
            }


            String subject = "Advertencia: El 80% de las horas han sido consumidas. Quedan solo " + bolsaHora.getHorasRestantes() + " horas.";
            String body = "Hola " + cliente.getNombre() + ",\n\n" +
                    "Te informamos que las horas del proyecto con la descripcion:\n" +
                    "Nombre de proyecto: " + proyecto.getNombre() + "\n" +
                    "descripcion: " + proyecto.getDescripcion() + "\n\n" +
                    "horas restantes: " + horasRestantes + "\n\n" +
                    "Si tienes alguna pregunta o problema, no dudes en contactarnos.\n\n" +
                    "Saludos,\n" +
                    "El equipo de soporte";
            enviarCorreo(cliente, subject, body);
            // Notificación cuando se alcanza el 80% de las horas consumidas
            return "Advertencia: El 80% de las horas han sido consumidas. Quedan solo " + bolsaHora.getHorasRestantes() + " horas.";
        }

        return "Horas consumidas exitosamente. Quedan " + bolsaHora.getHorasRestantes() + " horas.";
    }

    private void enviarCorreo(Cliente cliente, String subject, String body) throws CorreoUsuarioNoDisponibleException {
        String to = cliente.getEmail();
        if (to == null || to.isEmpty()) {
            throw new CorreoUsuarioNoDisponibleException("El correo de notificacion no esta disponible");
        }

        emailService.sendEmailwithAttachment(to, subject, body);
    }
}
