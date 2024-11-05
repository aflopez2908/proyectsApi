package gestorfreelance.gestorfreelancev5.service;

import gestorfreelance.gestorfreelancev5.model.Usuario;
import gestorfreelance.gestorfreelancev5.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {

        Usuario user = usuariosRepository.findByNombre(nombre)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + nombre + " no existe."));

        Collection<? extends GrantedAuthority> authorities = user.getRol()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getNombre().name())))
                .collect(Collectors.toSet());
        return new User(user.getNombre(),
                user.getContraseña(),
                true,
                true,
                true,
                true,
                authorities);
    }
}