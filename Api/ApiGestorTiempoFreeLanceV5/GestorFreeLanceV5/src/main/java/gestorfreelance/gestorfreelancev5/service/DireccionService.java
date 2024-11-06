package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.model.Direccion;
import gestorfreelance.gestorfreelancev5.repository.DireccionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionService {

    @Autowired
    private DireccionesRepository direccionRepository;

    public Direccion createDireccion(Direccion direccion) {
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
        direccion.setCiudad(direccionDetails.getCiudad());

        return direccionRepository.save(direccion);
    }

    public void deleteDireccion(Integer id) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Direccion no encontrada con id: " + id));
        direccionRepository.delete(direccion);
    }
}
