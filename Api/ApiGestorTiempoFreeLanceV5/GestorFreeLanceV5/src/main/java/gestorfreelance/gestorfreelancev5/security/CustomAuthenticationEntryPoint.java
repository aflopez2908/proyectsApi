package gestorfreelance.gestorfreelancev5.security;

import gestorfreelance.gestorfreelancev5.exception.UsuarioBloqueadoException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;


public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // Verificar si la causa es UsuarioBloqueadoException
        Throwable cause = authException.getCause();
        if (cause instanceof UsuarioBloqueadoException) {
            response.sendError(423, "Usuario bloqueado por multiples intentos fallidos");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acceso denegado");
        }
    }
}