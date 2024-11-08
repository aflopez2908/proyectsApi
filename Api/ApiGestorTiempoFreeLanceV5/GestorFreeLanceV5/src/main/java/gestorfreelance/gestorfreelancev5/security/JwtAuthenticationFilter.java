package gestorfreelance.gestorfreelancev5.security;

import gestorfreelance.gestorfreelancev5.model.Usuario;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gestorfreelance.gestorfreelancev5.repository.UsuariosRepository;
import gestorfreelance.gestorfreelancev5.service.IntentoLoginService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JwtUtils jwtUtils;
    private IntentoLoginService intentoLoginService;
    private UsuariosRepository usuariosRepository;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, IntentoLoginService intentoLoginService, UsuariosRepository usuariosRepository){
        this.jwtUtils = jwtUtils;
        this.intentoLoginService = intentoLoginService;
        this.usuariosRepository = usuariosRepository;

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        System.out.println("Autentificacion filter");
        Usuario usuario = null;
        String nombre = "";
        String contraseña = "";
        try{
            usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            nombre = usuario.getNombre();
            contraseña = usuario.getContraseña();

/*            Usuario usertest = usuariosRepository.findByNombre(nombre);*/

            Usuario usertest = usuariosRepository.findByNombre(nombre)
                    .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe."));
            System.out.println("Usuario encontrado:" + usertest);
            usuario.setUsuarioId(usertest.getUsuarioId());
            usuario.setEmail(usertest.getEmail());
            usuario.setRol(usertest.getRol());
            usuario.setFechaCreacion(usertest.getFechaCreacion());

            System.out.println("El usuario que se lee es:" + usuario);
            if (intentoLoginService.isUsuarioBloqueado(usuario)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Usuario bloqueado por múltiples intentos fallidos");
                response.getWriter().flush();
                throw new RuntimeException("Usuario bloqueado");
            }
            System.out.println("Proceso bien");
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
/*        System.out.println(nombre +" " + contraseña);
        String CheckPw = new BCryptPasswordEncoder().encode(contraseña);
        System.out.println("PW: " + CheckPw);
        Usuario usercheck = usuariosRepository.findByNombreAndContraseña(nombre, CheckPw);
        System.out.println("Usuario filter: " + usercheck);
        if (usercheck == null)
        {  System.out.println("Login Fallido");
            //intentoLoginService.registrarIntento(usuario);
        }*/


/*        intentoLoginService.registrarIntento(usuario);
        boolean bloqueado = intentoLoginService.isUsuarioBloqueado(usuario);
        if (bloqueado) {
            throw new UsuarioBloqueadoException("Usuario bloqueado por múltiples intentos fallidos");
        }*/

/*        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(nombre, contraseña);
        System.out.println("Objeto Autentificacion" + authenticationToken);
        System.out.println("Test" + getAuthenticationManager().authenticate(authenticationToken));
        return getAuthenticationManager().authenticate(authenticationToken);*/

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(nombre, contraseña);
            return getAuthenticationManager().authenticate(authenticationToken);

        } catch (AuthenticationException ex) {
            // Registrar intento fallido si la autenticación falla
            intentoLoginService.registrarIntento(usuario);
            throw ex; // Re-lanzar la excepción para manejarla adecuadamente en el flujo de Spring Security
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        System.out.println("Autentificacion Succesful");
        User user = (User) authResult.getPrincipal();
        String token = jwtUtils.generateAccesToken(user.getUsername());

        response.addHeader("Authorization", token);

        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("Message", "Autenticacion Correcta");
        httpResponse.put("Username", user.getUsername());

        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
