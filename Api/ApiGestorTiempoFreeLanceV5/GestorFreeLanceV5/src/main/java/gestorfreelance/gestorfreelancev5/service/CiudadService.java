package gestorfreelance.gestorfreelancev5.service;
import gestorfreelance.gestorfreelancev5.model.Ciudad;
import gestorfreelance.gestorfreelancev5.repository.CiudadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadService {
    @Autowired
    private CiudadesRepository ciudadRepository;

    public Ciudad createCiudad(Ciudad ciudad) {
        return ciudadRepository.save(ciudad);
    }

    public List<Ciudad> getAllCiudades() {
        return ciudadRepository.findAll();
    }

    public Optional<Ciudad> getCiudadById(Integer id) {
        return ciudadRepository.findById(id);
    }

    public Ciudad updateCiudad(Integer id, Ciudad ciudadDetails) {
        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada con id: " + id));

        ciudad.setNombre(ciudadDetails.getNombre());
        ciudad.setPais(ciudadDetails.getPais());

        return ciudadRepository.save(ciudad);
    }

    public void deleteCiudad(Integer id) {
        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada con id: " + id));
        ciudadRepository.delete(ciudad);
    }
}
