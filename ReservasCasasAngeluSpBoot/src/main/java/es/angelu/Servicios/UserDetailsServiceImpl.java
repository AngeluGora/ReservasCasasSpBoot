package es.angelu.Servicios;

import es.angelu.Entities.Usuario;
import es.angelu.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Cargar un usuario de la bd
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findUsuarioByNombreUsuario(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return User.withUsername(usuario.getNombreUsuario())
                .password(usuario.getPassword())
                .roles(usuario.getRol())
                .build();
    }
}
