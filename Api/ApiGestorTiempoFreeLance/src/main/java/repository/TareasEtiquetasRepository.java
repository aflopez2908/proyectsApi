/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.Etiquetas;
import entity.Tareas;
import entity.TareasEtiquetas;
import entity.TareasEtiquetasId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("TareasEtiquetasRepository")
public interface TareasEtiquetasRepository extends JpaRepository<TareasEtiquetas, TareasEtiquetasId> {
    List<TareasEtiquetas> findByTarea(Tareas tarea);
    List<TareasEtiquetas> findByEtiqueta(Etiquetas etiqueta);
}
