package es.angelu.Repository;

import es.angelu.Entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//@Repository
public interface ReservasDAO extends JpaRepository <Reserva, Long> {


}
