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




/**
 *
 * @author pipel
 */
@Service
public class ProyectoService {

    @Autowired
    private ProyectosRepository proyectoRepository;

    @Autowired
    private EstadosProyectoRepository estadoProyectoRepository;

    @Autowired
    private ProyectoEstadoRepository proyectoEstadoRepository;

    //buscar todos los proyectos
    public List<Proyectos> getAllProyectos() {
        return proyectoRepository.findAll();
    }



    // Crear un nuevo proyecto
    public Proyectos crearProyecto(Proyectos proyecto) {
        // Guardar el proyecto
        Proyectos nuevoProyecto = proyectoRepository.save(proyecto);
        return nuevoProyecto;
    }


    // Actualizar un proyecto (incluido el cambio de estado)
    public Proyectos actualizarProyecto(Long proyectoId, Proyectos detallesProyecto) {
        // Buscar el proyecto existente por su ID
        Proyectos proyectoExistente = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con el ID: " + proyectoId));

        // Actualizar los detalles del proyecto existente con los datos del proyecto proporcionado
        proyectoExistente.setNombre(detallesProyecto.getNombre());
        proyectoExistente.setDescripcion(detallesProyecto.getDescripcion());
        proyectoExistente.setCliente(detallesProyecto.getCliente());
        proyectoExistente.setFechaInicio(detallesProyecto.getFechaInicio());
        proyectoExistente.setFechaFin(detallesProyecto.getFechaFin());
        proyectoExistente.setEstado(detallesProyecto.getEstado());

        // Guardar el proyecto actualizado en la base de datos
        Proyectos proyectoActualizado = proyectoRepository.save(proyectoExistente);

        // Retornar el proyecto actualizado
        return proyectoActualizado;
    }

    // Eliminar un proyecto
    public void eliminarProyecto(Long proyectoId) {
        Proyectos proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + proyectoId));
        proyectoRepository.delete(proyecto);
    }

    // Obtener un proyecto por ID
    public Proyectos obtenerProyectoPorId(Long proyectoId) {
        return proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + proyectoId));
    }





    // Método para registrar un cambio de estado en la tabla transaccional
    private void registrarEstado(Proyectos proyecto, EstadosProyecto estado, String comentario) {
        ProyectoEstado proyectoEstado = new ProyectoEstado();
        proyectoEstado.setProyecto(proyecto);
        proyectoEstado.setEstado(estado);
        proyectoEstado.setFechaCambio((java.sql.Date) new Date());
        proyectoEstado.setComentario(comentario);

        proyectoEstadoRepository.save(proyectoEstado);
    }
}

