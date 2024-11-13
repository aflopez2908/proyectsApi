package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.model.Ciudad;
import gestorfreelance.gestorfreelancev5.model.Direccion;
import gestorfreelance.gestorfreelancev5.repository.CiudadesRepository;
import gestorfreelance.gestorfreelancev5.repository.DireccionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionService {

    @Autowired
    private DireccionesRepository direccionRepository;
    @Autowired
    private CiudadesRepository ciudadesRepository;

    public Direccion createDireccion(Direccion direccion) {
        if (direccion.getCiudad() != null && direccion.getCiudad().getCiudadId() != null) {
            Ciudad ciudad = ciudadesRepository.findById(direccion.getCiudad().getCiudadId())
                    .orElseThrow(() -> new RuntimeException("Ciudad no encontrada con id: " + direccion.getCiudad().getCiudadId()));
            direccion.setCiudad(ciudad);
        }
        return direccionRepository.save(direccion);
    }

    public List<Direccion> getAllDirecciones() {
        return direccionRepository.findAll();
    }

    public Optional<Direccion> getDireccionById(Integer id) {
        return direccionRepository.findById(id);
    }

    public Direccion updateDireccion(Integer id, Direccion direccionDetails) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Direccion no encontrada con id: " + id));
        direccion.setCalle(direccionDetails.getCalle());
        if (direccionDetails.getCiudad() != null && direccionDetails.getCiudad().getCiudadId() != null) {
            Ciudad ciudad = ciudadesRepository.findById(direccionDetails.getCiudad().getCiudadId())
                    .orElseThrow(() -> new RuntimeException("Ciudad no encontrada con id: " + direccionDetails.getCiudad().getCiudadId()));
            direccion.setCiudad(ciudad);
        }
        return direccionRepository.save(direccion);
    }

    public void deleteDireccion(Integer id) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Direccion no encontrada con id: " + id));
        direccionRepository.delete(direccion);
    }
}
