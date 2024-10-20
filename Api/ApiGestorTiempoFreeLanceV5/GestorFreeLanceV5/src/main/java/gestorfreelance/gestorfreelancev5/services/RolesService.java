package gestorfreelance.gestorfreelancev5.services;



import gestorfreelance.gestorfreelancev5.model.Roles;
import gestorfreelance.gestorfreelancev5.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

    @Service
    public class RolesService {

        @Autowired
        private RolesRepository rolesRepository;

        public Roles obtenerPorId(Integer rolId) {
            Optional<Roles> rolOptional = rolesRepository.findById(rolId);
            return rolOptional.orElse(null); // Retorna el rol si existe, o null si no se encuentra
        }

    }