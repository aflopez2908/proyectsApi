package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.model.IntentoLogin;
import gestorfreelance.gestorfreelancev5.model.Usuario;
import gestorfreelance.gestorfreelancev5.repository.IntentoLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IntentoLoginService {

    @Autowired
    private IntentoLoginRepository intentoLoginRepository;

    public IntentoLogin registrarIntento(Usuario usuario) {
        Optional<IntentoLogin> intentoExistente = intentoLoginRepository.findByUsuarioAndVigenteTrue(usuario);

        IntentoLogin intento;
        if (intentoExistente.isPresent()) {
            intento = intentoExistente.get();
            intento.setContador(intento.getContador() + 1);

            // Verificar si el contador excede el límite permitido
            if (intento.getContador() >= 3) {
                intento.setBloqueado(true);
                intento.setVigente(false); // Marcar como no vigente una vez bloqueado
            }

        } else {
            intento = new IntentoLogin();
            intento.setUsuario(usuario);
            intento.setContador(1);
        }

        return intentoLoginRepository.save(intento);
    }

    public boolean isUsuarioBloqueado(Usuario usuario) {
        Optional<IntentoLogin> intento = intentoLoginRepository.findByUsuarioAndVigenteTrue(usuario);
        return intento.isPresent() && intento.get().isBloqueado();
    }

    public void resetearIntentos(Usuario usuario) {
        Optional<IntentoLogin> intento = intentoLoginRepository.findByUsuarioAndVigenteTrue(usuario);
        if (intento.isPresent()) {
            IntentoLogin intentoLogin = intento.get();
            intentoLogin.setContador(0);
            intentoLogin.setBloqueado(false);
            intentoLogin.setVigente(true);
            intentoLoginRepository.save(intentoLogin);
        }
    }
}
