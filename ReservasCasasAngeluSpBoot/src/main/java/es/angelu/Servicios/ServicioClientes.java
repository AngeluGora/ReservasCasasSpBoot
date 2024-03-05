package es.angelu.Servicios;

import es.angelu.Entities.Casa;
import es.angelu.Entities.Cliente;
import es.angelu.Repository.ClientesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioClientes {

    @Autowired
    private ClientesDAO clientesDAO;

    public boolean insertarActualizarCliente(Cliente cliente) {
        clientesDAO.save(cliente);
        return true;
    }

    public boolean actualizaCliente(Cliente cliente){
        clientesDAO.actualizaCliente(cliente);
        return true;
    }
    public List<Cliente> getAllClientes() {
        return clientesDAO.findAll();
    }

    public Cliente getCliente(long id) {
        return clientesDAO.findById(id).orElse(null);
    }

    public boolean eliminarCliente(long id) {
        clientesDAO.deleteById(id);
        return true;
    }
}
