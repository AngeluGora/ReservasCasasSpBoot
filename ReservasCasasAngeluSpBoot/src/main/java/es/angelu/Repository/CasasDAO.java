package es.angelu.Repository;

import es.angelu.Entities.Casa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//@Repository
public interface CasasDAO extends JpaRepository<Casa, Long> {


    @Modifying
    @Transactional
    @Query("UPDATE Casa c SET c.direccion = :direccion, c.numeroHabitaciones = :numHabitaciones, c.precioPorNoche = :precioPorNoche WHERE c.idC = :id")
    public void updateCasa(Long id, String direccion, int numHabitaciones, double precioPorNoche);

    default boolean actualizaCasa(Casa casa) {
        updateCasa(casa.getIdC(), casa.getDireccion(), casa.getNumeroHabitaciones(), casa.getPrecioPorNoche());
        return true;
    }

}

