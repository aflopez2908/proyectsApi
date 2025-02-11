package gestorfreelance.gestorfreelancev5.security;

import gestorfreelance.gestorfreelancev5.repository.UsuariosRepository;
import gestorfreelance.gestorfreelancev5.security.JwtAuthenticationFilter;
import gestorfreelance.gestorfreelancev5.security.JwtAuthorizationFilter;
import gestorfreelance.gestorfreelancev5.security.JwtUtils;
import gestorfreelance.gestorfreelancev5.service.EmailService;
import gestorfreelance.gestorfreelancev5.service.IntentoLoginService;
import gestorfreelance.gestorfreelancev5.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    IntentoLoginService intentoLoginService;
    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtAuthorizationFilter authorizationFilter;

    @Autowired
    EmailService emailService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils,intentoLoginService,usuariosRepository);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");
        jwtAuthenticationFilter.setEmailService(emailService);
        return httpSecurity
                .csrf(config -> config.disable())
                .authorizeHttpRequests(auth -> {
                //    auth.requestMatchers("/createUser", "/api/v1/user", "/api/v1/task", "/api/v1/user/createUser","/login").permitAll();
                    auth.requestMatchers("/createUser"
                            ,"/login"
                            ,"/v3/api-docs/**"
                            ,"/swagger-ui/**"
                            ,"/swagger-ui/index.html").permitAll();
              //      auth.requestMatchers(HttpMethod.PUT, "/api/v1/user/**").hasAuthority("ADMIN"); // Asegura que solo los ADMIN accedan
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

   public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("1234"));
    }
}