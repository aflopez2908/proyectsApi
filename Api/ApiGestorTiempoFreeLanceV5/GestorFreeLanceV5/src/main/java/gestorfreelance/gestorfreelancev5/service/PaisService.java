package gestorfreelance.gestorfreelancev5.service;
import gestorfreelance.gestorfreelancev5.model.Pais;
import gestorfreelance.gestorfreelancev5.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisService {

    @Autowired
    private PaisRepository paisRepository;

    public Pais createPais(Pais pais) {
        Optional<Pais> paisExistente = paisRepository.findByNombre(pais.getNombre());
        if (paisExistente.isPresent()) {
            throw new RuntimeException("El país ya existe.");
        }
        return paisRepository.save(pais);
    }

    public List<Pais> getAllPaises() {
        return paisRepository.findAll();
    }

    public Optional<Pais> getPaisById(Integer id) {
        return paisRepository.findById(id);
    }

    public Pais updatePais(Integer id, Pais paisDetails) {
        Pais pais = paisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pais no encontrado con id: " + id));

        pais.setNombre(paisDetails.getNombre());

        return paisRepository.save(pais);
    }

    public void deletePais(Integer id) {
        Pais pais = paisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pais no encontrado con id: " + id));
        paisRepository.delete(pais);
    }
}
