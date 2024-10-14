package gestorfreelance.gestorfreelancev5.model;

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
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Facturacion")
public class Facturacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer facturaId;

    @ManyToOne
    @JoinColumn(name = "proyecto_id")
    private Proyectos proyecto;

    //@Column(precision = 10, scale = 2)
    @Column
    private Double monto;

    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision;
/*    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;    */
@Column(name = "fecha_vencimiento")
private LocalDateTime fechaVencimiento;
}
