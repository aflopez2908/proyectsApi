package gestorfreelance.gestorfreelancev5.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class UserRolId implements Serializable {
    private static final long serialVersionUID = -8075772688175838797L;
    @NotNull
    @Column(name = "rol_id", nullable = false)
    private Long rolId;

    @NotNull
    @Column(name = "usuario_id", nullable = false)
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserRolId entity = (UserRolId) o;
        return Objects.equals(this.rolId, entity.rolId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolId, userId);
    }

}