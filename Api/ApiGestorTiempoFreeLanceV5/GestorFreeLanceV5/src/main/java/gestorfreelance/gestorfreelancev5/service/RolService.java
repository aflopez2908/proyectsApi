package gestorfreelance.gestorfreelancev5.service;



import gestorfreelance.gestorfreelancev5.model.Rol;
import gestorfreelance.gestorfreelancev5.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

    @Service
    public class RolService {

        @Autowired
        private RolesRepository rolesRepository;

/*        public Rol obtenerPorId(Integer rolId) {
            Optional<Rol> rolOptional = rolesRepository.findById(rolId);
            return rolOptional.orElse(null); // Retorna el rol si existe, o null si no se encuentra
        }*/

    }