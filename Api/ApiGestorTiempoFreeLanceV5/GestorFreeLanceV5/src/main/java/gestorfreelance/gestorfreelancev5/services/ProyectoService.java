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
import java.util.List;

@Service
public class ProyectoService {

    @Autowired
    private ProyectosRepository proyectoRepository;

    @Autowired
    private EstadosProyectoRepository estadoProyectoRepository;

    @Autowired
    private ProyectoEstadoRepository proyectoEstadoRepository;

    public List<Proyectos> getAllProyectos() {
        return proyectoRepository.findAll();
    }
    public Proyectos obtenerProyectoPorId(Long proyectoId) {
        return proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + proyectoId));
    }
    public Proyectos crearProyecto(Proyectos proyecto) {
        Proyectos proyectoExistente= proyectoRepository.findByNombre(proyecto.getNombre());
        if (proyectoExistente != null) {
            throw new IllegalArgumentException("El proyecto ya existe y no se puede crear.");
        }
        EstadosProyecto estadosProyecto= estadoProyectoRepository.findById(1);
        Proyectos nuevoProyecto = proyectoRepository.save(proyecto);
        registrarEstado(proyecto,estadosProyecto, "Creacion del proyecto",1);
        return nuevoProyecto;
    }
    public Proyectos actualizarProyecto(Long proyectoId, Proyectos detallesProyecto) {
        Proyectos proyectoExistente = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con el ID: " + proyectoId));
        proyectoExistente.setNombre(detallesProyecto.getNombre());
        proyectoExistente.setDescripcion(detallesProyecto.getDescripcion());
        proyectoExistente.setCliente(detallesProyecto.getCliente());
        proyectoExistente.setFechaInicio(detallesProyecto.getFechaInicio());
        proyectoExistente.setFechaFin(detallesProyecto.getFechaFin());
        Proyectos proyectoActualizado = proyectoRepository.save(proyectoExistente);
        return proyectoActualizado;
    }
    public void eliminarProyecto(Long proyectoId) {
        Proyectos proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + proyectoId));
        proyectoRepository.delete(proyecto);
    }
    private void registrarEstado(Proyectos proyecto, EstadosProyecto estado, String comentario, int vigencia) {
        ProyectoEstado proyectoEstado = new ProyectoEstado();
        proyectoEstado.setProyecto(proyecto);
        proyectoEstado.setEstado(estado);
        proyectoEstado.setFechaCambio(new java.sql.Date(System.currentTimeMillis()));
        proyectoEstado.setComentario(comentario);
        proyectoEstado.setVigencia(vigencia);
        proyectoEstadoRepository.save(proyectoEstado);
    }
}

