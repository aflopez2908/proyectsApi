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
@Table(name="Rol_Permiso")
public class RolPermiso {
    @EmbeddedId
    private RolPermisoId id;

    @ManyToOne
    @MapsId("rolId")
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @ManyToOne
    @MapsId("permisoId")
    @JoinColumn(name = "permiso_id")
    private Permiso permiso;
}
