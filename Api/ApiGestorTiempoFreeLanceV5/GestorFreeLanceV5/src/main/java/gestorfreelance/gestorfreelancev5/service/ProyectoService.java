package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.exception.ResourceNotFoundException;
import gestorfreelance.gestorfreelancev5.model.EstadoProyecto;
import gestorfreelance.gestorfreelancev5.model.Proyecto;
import gestorfreelance.gestorfreelancev5.model.ProyectoEstado;
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

    public List<Proyecto> getAllProyectos() {
        return proyectoRepository.findAll();
    }
    public Proyecto obtenerProyectoPorId(Long proyecto_id) {
        return proyectoRepository.findById(proyecto_id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + proyecto_id));
    }
    public Proyecto crearProyecto(Proyecto proyecto) {
        Proyecto proyectoExistente= proyectoRepository.findByNombre(proyecto.getNombre());
        if (proyectoExistente != null) {
            throw new IllegalArgumentException("El proyecto ya existe y no se puede crear.");
        }
        EstadoProyecto estadoProyecto = estadoProyectoRepository.findById(1);
        Proyecto nuevoProyecto = proyectoRepository.save(proyecto);
        registrarEstado(proyecto, estadoProyecto, "Creacion del proyecto",1);
        return nuevoProyecto;
    }
    public Proyecto actualizarProyecto(Long proyecto_id, Proyecto detallesProyecto) {
        Proyecto proyectoExistente = proyectoRepository.findById(proyecto_id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con el ID: " + proyecto_id));
        proyectoExistente.setNombre(detallesProyecto.getNombre());
        proyectoExistente.setDescripcion(detallesProyecto.getDescripcion());
        proyectoExistente.setCliente(detallesProyecto.getCliente());
        proyectoExistente.setFechaInicio(detallesProyecto.getFechaInicio());
        proyectoExistente.setFechaFin(detallesProyecto.getFechaFin());
        Proyecto proyectoActualizado = proyectoRepository.save(proyectoExistente);
        return proyectoActualizado;
    }
    public void eliminarProyecto(Long proyecto_id) {
        Proyecto proyecto = proyectoRepository.findById(proyecto_id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + proyecto_id));
        proyectoRepository.delete(proyecto);
    }
    private void registrarEstado(Proyecto proyecto, EstadoProyecto estado, String comentario, int vigencia) {
        ProyectoEstado proyectoEstado = new ProyectoEstado();
        proyectoEstado.setProyecto(proyecto);
        proyectoEstado.setEstado(estado);
        proyectoEstado.setFechaCambio(new java.sql.Date(System.currentTimeMillis()));
        proyectoEstado.setComentario(comentario);
        proyectoEstado.setVigencia(vigencia);
        proyectoEstadoRepository.save(proyectoEstado);
    }
}

