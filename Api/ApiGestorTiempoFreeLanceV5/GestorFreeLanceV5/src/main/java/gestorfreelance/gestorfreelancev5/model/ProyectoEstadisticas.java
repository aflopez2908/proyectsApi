package gestorfreelance.gestorfreelancev5.model;

public class ProyectoEstadisticas {
    private long totalProyectos;
    private long proyectosActivos;
    private long proyectosFinalizados;
    private double duracionPromedio;

    // Constructor, getters y setters
    public ProyectoEstadisticas(long totalProyectos, long proyectosActivos, long proyectosFinalizados, double duracionPromedio) {
        this.totalProyectos = totalProyectos;
        this.proyectosActivos = proyectosActivos;
        this.proyectosFinalizados = proyectosFinalizados;
        this.duracionPromedio = duracionPromedio;
    }

    public long getTotalProyectos() {
        return totalProyectos;
    }

    public long getProyectosActivos() {
        return proyectosActivos;
    }

    public long getProyectosFinalizados() {
        return proyectosFinalizados;
    }

    public double getDuracionPromedio() {
        return duracionPromedio;
    }
}
