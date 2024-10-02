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
@Table(name = "Respuestas_Encuesta")
public class RespuestasEncuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer respuestaId;

    @ManyToOne
    @JoinColumn(name = "pregunta_id")
    private PreguntasEncuesta pregunta;

    @Column(columnDefinition = "TEXT")
    private String respuesta;
}
