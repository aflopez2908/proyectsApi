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
@Table(name="Documentos")
public class Documentos {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer documentoId;

    @ManyToOne
    @JoinColumn(name = "tarea_id")
    private Tareas tarea;

    @Column(nullable = false, length = 255)
    private String nombreArchivo;

    @Column(nullable = false, length = 255)
    private String rutaArchivo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSubida;

    @ManyToOne
    @JoinColumn(name = "subido_por")
    private Usuarios subidoPor;    
}
