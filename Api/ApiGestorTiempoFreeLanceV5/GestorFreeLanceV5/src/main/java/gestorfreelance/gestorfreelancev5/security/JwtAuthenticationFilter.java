package gestorfreelance.gestorfreelancev5.security;

import gestorfreelance.gestorfreelancev5.model.Usuario;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gestorfreelance.gestorfreelancev5.repository.UsuariosRepository;
import gestorfreelance.gestorfreelancev5.service.EmailService;
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
    private EmailService emailService;
    public JwtAuthenticationFilter(JwtUtils jwtUtils, IntentoLoginService intentoLoginService, UsuariosRepository usuariosRepository){
        this.jwtUtils = jwtUtils;
        this.intentoLoginService = intentoLoginService;
        this.usuariosRepository = usuariosRepository;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
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


            Usuario usertest = usuariosRepository.findByNombre(nombre)
                    .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe."));
            System.out.println("Usuario encontrado:" + usertest);
            usuario.setUsuarioId(usertest.getUsuarioId());
            usuario.setEmail(usertest.getEmail());
            usuario.setRol(usertest.getRol());
            usuario.setFechaCreacion(usertest.getFechaCreacion());

            System.out.println("El usuario que se lee es:" + usuario);
            if (intentoLoginService.isUsuarioBloqueado(usuario)) {
                // correo de usuario bloqueado por itentos fallidos
                String subject = "Notificación de bloqueo de cuenta";
                String body = "Su cuenta ha sido bloqueada debido a múltiples intentos fallidos de inicio de sesión.  Comuniquese con un  Adminitrador para desbloquear su cuenta ";
                emailService.sendEmailwithAttachment(usuario.getEmail(), subject, body);

                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                response.getWriter().write("Usuario bloqueado por multiples intentos fallidos");
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
            intentoLoginService.registrarIntento(usuario);
            throw ex;
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
