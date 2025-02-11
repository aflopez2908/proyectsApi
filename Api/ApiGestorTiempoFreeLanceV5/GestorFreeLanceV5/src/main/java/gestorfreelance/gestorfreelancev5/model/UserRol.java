package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "UserRol")
public class UserRol {
    @SequenceGenerator(name = "user_roles_id_gen", sequenceName = "reseñas_id_reseña_seq", allocationSize = 1)
    @EmbeddedId
    private UserRolId id;

    @MapsId("rolId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol role;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario user;

}
