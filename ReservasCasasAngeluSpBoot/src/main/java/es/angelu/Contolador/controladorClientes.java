package es.angelu.Contolador;
import es.angelu.Entities.Cliente;
import es.angelu.Servicios.ServicioClientes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class controladorClientes {
    @Autowired
    ServicioClientes servicioClientes;

    String usuario;
    String rol;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/agregarCliente")
    public String agregarCliente(Model modelo) {
        modelo.addAttribute("elCliente", new Cliente());
        //if (usuario.equals("admin"))
            return "formularioCliente"; //JSP
       // else return "redirect:/login";
    }

    @PostMapping("/agregarCliente")
    public String procesarAgregarCliente(@ModelAttribute("elCliente") @Valid Cliente cliente, BindingResult result) {
        if (!result.hasErrors()) {
            if (servicioClientes.insertarActualizarCliente(cliente)) {
                return "redirect:/verClientes";
            } else {
                throw new RuntimeException("Error al guardar el cliente");
            }
        } else {
            return "formularioCliente"; //JSP
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/verClientes")
    public String mostrarClientes(Model modelo) {
        List<Cliente> clientes = servicioClientes.getAllClientes();
        modelo.addAttribute("losClientes", clientes);
        return "tablaClientes"; //JSP
    }

    @GetMapping("/editarCliente/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") long idCliente, Model model) {
        Cliente cliente = servicioClientes.getCliente(idCliente);
        if (cliente != null) {
            model.addAttribute("elCliente", cliente);
            return "formularioEditarCliente"; //JSP
        } else {
            throw new RuntimeException("Error en la edición del cliente");
        }
    }

    @PostMapping("/editarCliente")
    public String procesarEditarCliente(@ModelAttribute("elCliente") Cliente cliente) {
        if (servicioClientes.actualizaCliente(cliente)) {
            return "redirect:/verClientes";
        } else {
            throw new RuntimeException("Error al editar el cliente");
        }
    }

    @GetMapping("/eliminarCliente/{id}")
    public String eliminarCliente(@PathVariable long id) {
        if (servicioClientes.eliminarCliente(id)) {
            return "redirect:/verClientes";
        } else {
            throw new RuntimeException("Error al eliminar el cliente");
        }
    }
}
