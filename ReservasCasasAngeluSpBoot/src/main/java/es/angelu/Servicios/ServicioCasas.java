package es.angelu.Servicios;

import es.angelu.Entities.Casa;
import es.angelu.Repository.CasasDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioCasas {

    @Autowired
    CasasDAO casasDAO;

    public boolean insertarActualizarCasa(Casa casa) {
        casasDAO.save(casa);
        return true;
    }
    public boolean actualizaCasa(Casa casa){
        casasDAO.actualizaCasa(casa);
        return true;
    }

    public List<Casa> getAllCasas() {
        return casasDAO.findAll();
    }

    public Casa getCasa(long id) {
        return casasDAO.findById(id).orElse(null);
    }

    public boolean eliminarCasa(long id) {
        casasDAO.deleteById(id);
        return true;
    }
}
