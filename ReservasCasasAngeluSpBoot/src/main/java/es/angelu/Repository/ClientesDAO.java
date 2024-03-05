package es.angelu.Repository;

import es.angelu.Entities.Casa;
import es.angelu.Entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//@Repository
public interface ClientesDAO extends JpaRepository<Cliente, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Cliente c SET c.email = :email, c.nombre = :nombre, c.telefono = :telefono WHERE c.idCli = :id")
    public void updateCliente(Long id, String email, String nombre, String telefono);

    default boolean actualizaCliente(Cliente cliente) {
        updateCliente(cliente.getIdCli(), cliente.getEmail(), cliente.getNombre(), cliente.getTelefono());
        return true;
    }
}
