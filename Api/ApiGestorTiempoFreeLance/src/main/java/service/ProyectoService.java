/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.EstadosProyecto;
import entity.ProyectoEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProyectoRepository;
import entity.Proyectos;
import java.util.Date;
import java.util.List;
import repository.EstadosProyectoRepository;
import repository.ProyectoEstadoRepository;

/**
 *
 * @author pipel
 */
@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private EstadosProyectoRepository estadoProyectoRepository;

    @Autowired
    private ProyectoEstadoRepository proyectoEstadoRepository;

    // Crear un nuevo proyecto
    public Proyectos crearProyecto(Proyectos proyecto) {
        // Guardar el proyecto
        Proyectos nuevoProyecto = proyectoRepository.save(proyecto);

        // Registrar el estado inicial en la tabla transaccional
        registrarEstado(nuevoProyecto, proyecto.getEstado(), "Proyecto creado");

        return nuevoProyecto;
    }

    // Actualizar un proyecto (incluido el cambio de estado)
    public Proyectos actualizarProyecto(Long proyectoId, Proyectos detallesProyecto) {
        Proyectos proyectoExistente = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + proyectoId));

        // Actualizar detalles del proyecto
        proyectoExistente.setNombre(detallesProyecto.getNombre());
        proyectoExistente.setDescripcion(detallesProyecto.getDescripcion());

        // Verificar si el estado ha cambiado
        if (!proyectoExistente.getEstado().getEstadoId().equals(detallesProyecto.getEstado().getEstadoId())) {
            proyectoExistente.setEstado(detallesProyecto.getEstado());

            // Registrar el cambio de estado
            registrarEstado(proyectoExistente, detallesProyecto.getEstado(), "Estado cambiado por actualización");
        }

        return proyectoRepository.save(proyectoExistente);
    }

    // Eliminar un proyecto
    public void eliminarProyecto(Long proyectoId) {
        Proyectos proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + proyectoId));
        
        proyectoRepository.delete(proyecto);

        // Registrar el estado de eliminación
        registrarEstado(proyecto, proyecto.getEstado(), "Proyecto eliminado");
    }

    // Obtener un proyecto por ID
    public Proyectos obtenerProyectoPorId(Long proyectoId) {
        return proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + proyectoId));
    }
    


    public List<Proyectos> getAllProyectos() {
        return proyectoRepository.findAll();
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
