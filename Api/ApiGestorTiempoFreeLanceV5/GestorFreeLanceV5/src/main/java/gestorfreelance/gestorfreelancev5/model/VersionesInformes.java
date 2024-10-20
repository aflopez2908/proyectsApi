package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Versiones_Informes")
public class VersionesInformes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer versionId;

    @ManyToOne
    @JoinColumn(name = "informe_id")
    private Informes informe;

    @Column(length = 10)
    private String version;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
}
