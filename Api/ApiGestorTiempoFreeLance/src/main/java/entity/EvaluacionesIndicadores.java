/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Column;
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
@Table(name="Evaluaciones_Indicadores")
public class EvaluacionesIndicadores {

    @EmbeddedId
    private EvaluacionesIndicadoresId id;

    @ManyToOne
    @MapsId("evaluacionId")
    @JoinColumn(name = "evaluacion_id")
    private EvaluacionesDesempeno evaluacion;

    @ManyToOne
    @MapsId("indicadorId")
    @JoinColumn(name = "indicador_id")
    private IndicadoresDesempeno indicador;

    @Column(precision = 10, scale = 2)
    private Double valor;    
}
