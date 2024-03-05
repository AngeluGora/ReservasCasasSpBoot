package es.angelu.Servicios;

import es.angelu.Entities.Reserva;
import es.angelu.Repository.ReservasDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioReservas {

    @Autowired
    ReservasDAO reservasDAO;

    public boolean insertarActualizarReserva(Reserva reserva) {
        reservasDAO.save(reserva);
        return true;
    }

    public List<Reserva> getAllReservas() {
        return reservasDAO.findAll();
    }

    public Reserva getReserva(long id) {
        return reservasDAO.findById(id).orElse(null);
    }

    public boolean eliminarReserva(long id) {
        reservasDAO.deleteById(id);
        return true;
    }
}
