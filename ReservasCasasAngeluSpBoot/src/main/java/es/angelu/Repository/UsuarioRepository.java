package es.angelu.Repository;

import es.angelu.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findUsuarioByNombreUsuario(String nombre);
}
