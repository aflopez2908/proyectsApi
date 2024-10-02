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
@Table(name="Proyectos_Tipos")
public class ProyectosTipos {
    @EmbeddedId
    private ProyectosTiposId id;

    @ManyToOne
    @MapsId("proyectoId")
    @JoinColumn(name = "proyecto_id")
    private Proyectos proyecto;

    @ManyToOne
    @MapsId("tipoId")
    @JoinColumn(name = "tipo_id")
    private TiposProyecto tipo;    
}
