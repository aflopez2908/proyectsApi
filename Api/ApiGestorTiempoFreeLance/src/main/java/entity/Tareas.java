/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.sql.Date;
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
@Table(name="Tareas")
public class Tareas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tareaId;

    @ManyToOne
    @JoinColumn(name = "proyecto_id")
    private Proyectos proyecto;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadosTarea estado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;

    @ManyToOne
    @JoinColumn(name = "asignado_a")
    private Usuarios asignadoA;
}
