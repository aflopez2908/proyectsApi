/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.PrioridadesTarea;
import entity.Tareas;
import entity.TareasPrioridades;
import entity.TareasPrioridadesId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Seidor Colombia
 */
@Repository("TareasPrioridadesRepository")
public interface TareasPrioridadesRepository extends JpaRepository<TareasPrioridades, TareasPrioridadesId> {
    List<TareasPrioridades> findByTarea(Tareas tarea);
    List<TareasPrioridades> findByPrioridad(PrioridadesTarea prioridad);
}
