package gestorfreelance.gestorfreelancev5.model;

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


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Tarea_Etiqueta")
public class TareaEtiqueta {

    @EmbeddedId
    private TareaEtiquetaId id;

    @ManyToOne
    @MapsId("tareaId")
    @JoinColumn(name = "tarea_id")
    private Tarea tarea;

    @ManyToOne
    @MapsId("etiquetaId")
    @JoinColumn(name = "etiqueta_id")
    private Etiqueta etiqueta;
}
