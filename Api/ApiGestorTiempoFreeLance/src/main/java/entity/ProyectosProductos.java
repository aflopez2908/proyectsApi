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
@Table(name="Proyectos_Productos")
public class ProyectosProductos {
   @EmbeddedId
    private ProyectosProductosId id;

    @ManyToOne
    @MapsId("proyectoId")
    @JoinColumn(name = "proyecto_id")
    private Proyectos proyecto;

    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "producto_id")
    private Productos producto;   
}
