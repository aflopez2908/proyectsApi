/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Seidor Colombia
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Tareas_Prioridades")
public class TareasPrioridades {
    @EmbeddedId
    private TareasPrioridadesId id;

    @ManyToOne
    @MapsId("tareaId")
    @JoinColumn(name = "tarea_id")
    private Tareas tarea;

    @ManyToOne
    @MapsId("prioridadId")
    @JoinColumn(name = "prioridad_id")
    private PrioridadesTarea prioridad;   
}
