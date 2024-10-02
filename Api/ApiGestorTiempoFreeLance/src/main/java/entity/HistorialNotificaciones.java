/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

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
@Table(name = "Historial_Notificaciones")
public class HistorialNotificaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historialId;

    @ManyToOne
    @JoinColumn(name = "notificacion_id")
    private Notificaciones notificacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;
}
