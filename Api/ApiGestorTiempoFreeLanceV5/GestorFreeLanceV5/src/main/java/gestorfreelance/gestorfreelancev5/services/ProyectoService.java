package gestorfreelance.gestorfreelancev5.services;


import gestorfreelance.gestorfreelancev5.exceptions.ResourceNotFoundException;
import gestorfreelance.gestorfreelancev5.model.EstadosProyecto;
import gestorfreelance.gestorfreelancev5.model.ProyectoEstado;
import gestorfreelance.gestorfreelancev5.model.Proyectos;
import gestorfreelance.gestorfreelancev5.repository.EstadosProyectoRepository;
import gestorfreelance.gestorfreelancev5.repository.ProyectoEstadoRepository;
import gestorfreelance.gestorfreelancev5.repository.ProyectosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;




@Service
public class ProyectoService {

    @Autowired
    private ProyectosRepository proyectoRepository;

    @Autowired
    private EstadosProyectoRepository estadoProyectoRepository;

    @Autowired
    private ProyectoEstadoRepository proyectoEstadoRepository;

    public Proyectos crearProyecto(Proyectos proyecto) {
        Proyectos nuevoProyecto = proyectoRepository.save(proyecto);
        registrarEstado(nuevoProyecto, proyecto.getEstado(), "Proyecto creado");

        return nuevoProyecto;
    }

    public Proyectos actualizarProyecto(Long proyectoId, Proyectos detallesProyecto) {
        Proyectos proyectoExistente = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + proyectoId));

        proyectoExistente.setNombre(detallesProyecto.getNombre());
        proyectoExistente.setDescripcion(detallesProyecto.getDescripcion());

        if (!proyectoExistente.getEstado().getEstadoId().equals(detallesProyecto.getEstado().getEstadoId())) {
            proyectoExistente.setEstado(detallesProyecto.getEstado());
            registrarEstado(proyectoExistente, detallesProyecto.getEstado(), "Estado cambiado por actualización");
        }

        return proyectoRepository.save(proyectoExistente);
    }

    public void eliminarProyecto(Long proyectoId) {
        Proyectos proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + proyectoId));

        proyectoRepository.delete(proyecto);

        registrarEstado(proyecto, proyecto.getEstado(), "Proyecto eliminado");
    }

    public Proyectos obtenerProyectoPorId(Long proyectoId) {
        return proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + proyectoId));
    }



    public List<Proyectos> getAllProyectos() {
        return proyectoRepository.findAll();
    }
    private void registrarEstado(Proyectos proyecto, EstadosProyecto estado, String comentario) {
        ProyectoEstado proyectoEstado = new ProyectoEstado();
        proyectoEstado.setProyecto(proyecto);
        proyectoEstado.setEstado(estado);
        proyectoEstado.setFechaCambio((java.sql.Date) new Date());
        proyectoEstado.setComentario(comentario);

        proyectoEstadoRepository.save(proyectoEstado);
    }
}

