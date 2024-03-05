package es.angelu.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * El UserDetailsService es una interfaz de Spring Security que se utiliza
 * para cargar los detalles del usuario durante la autenticación.
 * Esta interfaz tiene un único método loadUserByUsername(String username) que
 * debe ser implementado para cargar de la BD los usuarios a partir del nombre propocionado
 * en la página de  login
 */
@Configuration
@EnableWebSecurity     // Habilita la configuración de Spring Security
@EnableMethodSecurity  //Habilita la seguidad basada en  métodos
public class ConfiguracionSeguridadBasadaEnMetodos {

    //  ---- primera parte: AUTENTICACIÓN ----------------------------------------

    //Inyecta los detalles del usuario en la autenticación.
    // Lo hace a través de la clase que implementa la interface UserDetailsService.
    // Esta clase es UserDetailsServiceImpl y está colocada en el paquete servicios
    @Autowired
    UserDetailsService detallesUsuario;  //contiene los detalles de usuario autenticado

    //Bean para encriptar la password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    Este método se utiliza para configurar el AuthenticationManagerBuilder, que es responsable
     de construir el AuthenticationManager que Spring Security utiliza para autenticar usuarios.
    Este método dice a Spring Security que utilice los detalles del usuario (username, password y role)
     durante el proceso de autenticación.
    En otras palabras, cuando un usuario intenta autenticarse, Spring Security utilizará
     el método loadUserByUsername de tu implementación de UserDetailsService para buscar
     los detalles del usuario en tu base de datos o sistema de autenticación.
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detallesUsuario);
    }

    //----- segunda parte: AUTORIZACIONES --------------------------------------
    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().permitAll())
                .formLogin(form -> form
                        .loginPage("/login"))
                .logout(logout -> logout
                        .logoutUrl("/logout") // Define la URL de logout personalizada
                        .logoutSuccessUrl("/") // Define la URL de éxito después del logout
                        .invalidateHttpSession(true) // Invalida la sesión HTTP durante el logout
                        .deleteCookies("JSESSIONID")

                );

        return http.build();
    }

    /*http.formLogin(withDefaults());
     */


}
