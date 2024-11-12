package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.model.Ciudad;
import gestorfreelance.gestorfreelancev5.model.Pais;
import gestorfreelance.gestorfreelancev5.repository.CiudadesRepository;
import gestorfreelance.gestorfreelancev5.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadService {
    @Autowired
    private CiudadesRepository ciudadRepository;

    @Autowired
    private PaisRepository paisRepository;

    public Ciudad createCiudad(Ciudad ciudad) {
        if (ciudad.getPais() != null && ciudad.getPais().getPaisId() != null) {
            Pais pais = paisRepository.findById(ciudad.getPais().getPaisId())
                    .orElseThrow(() -> new RuntimeException("País no encontrado con id: " + ciudad.getPais().getPaisId()));
            ciudad.setPais(pais);
        }
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
        if (ciudadDetails.getPais() != null && ciudadDetails.getPais().getPaisId() != null) {
            Pais pais = paisRepository.findById(ciudadDetails.getPais().getPaisId())
                    .orElseThrow(() -> new RuntimeException("País no encontrado con id: " + ciudadDetails.getPais().getPaisId()));

            ciudad.setPais(pais);
        }

        return ciudadRepository.save(ciudad);
    }

    public void deleteCiudad(Integer id) {
        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada con id: " + id));
        ciudadRepository.delete(ciudad);
    }
}
