package gestorfreelance.gestorfreelancev5.DTO;

import gestorfreelance.gestorfreelancev5.model.Proyecto;
import lombok.Data;

@Data
public class ProyectoDTO {
    //map strucre
    // json ignore
    private Proyecto proyecto;
    private Integer horasAsignadas;

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Integer getHorasAsignadas() {
        return horasAsignadas;
    }

    public void setHorasAsignadas(Integer horasAsignadas) {
        this.horasAsignadas = horasAsignadas;
    }
}
