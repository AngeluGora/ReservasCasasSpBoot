package es.angelu.Contolador;

import es.angelu.Entities.Casa;
import es.angelu.Entities.Cliente;
import es.angelu.Entities.Reserva;
import es.angelu.Repository.CasasDAO;
import es.angelu.Repository.ClientesDAO;
import es.angelu.Repository.ReservasDAO;
import es.angelu.Servicios.ServicioCasas;
import es.angelu.Servicios.ServicioClientes;
import es.angelu.Servicios.ServicioReservas;
import jakarta.servlet.http.HttpServletRequest;
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
public class controladorReservas {
    @Autowired
    ServicioReservas  servicioReservas;
    @Autowired
    ServicioClientes servicioClientes;
    @Autowired
    ServicioCasas servicioCasas;
    @Autowired
    Reserva reserva;
    String nombreUsuario, rolUsuario;


    @GetMapping("/regresarPagIndex")
    public String regresar(HttpServletRequest request) {
        return "redirect:/";
    }

    @RequestMapping("/")
    public String inicio() {
        // Obtener la autenticación actual
        Authentication usurioAutenticado = SecurityContextHolder.getContext().getAuthentication();
        //Obtener nombre y rol
        nombreUsuario = usurioAutenticado.getName();
        List roles = (List) usurioAutenticado.getAuthorities();
        rolUsuario = roles.get(0).toString();
        return "index";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/agregarReserva")
    public String agregarReserva(Model modelo) {
        List<Cliente> listaClientes = servicioClientes.getAllClientes();
        List<Casa> listaCasas = servicioCasas.getAllCasas();

        modelo.addAttribute("listaClientes", listaClientes);
        modelo.addAttribute("listaCasas", listaCasas);

        modelo.addAttribute("laReserva", new Reserva());
        return "formularioReserva"; //JSP
    }

    @PostMapping("/agregarReserva")
    public String procesarAgregarReserva(@ModelAttribute("laReserva") @Valid Reserva reserva, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            if (servicioReservas.insertarActualizarReserva(reserva)) {
                return "redirect:/verReservas";
            } else {
                throw new RuntimeException("Error al guardar la reserva");
            }
        } else {
            // Si hay errores de validación, recargar los datos de clientes y casas
            List<Cliente> listaClientes = servicioClientes.getAllClientes();
            List<Casa> listaCasas = servicioCasas.getAllCasas();

            // Agregar los clientes y las casas al modelo
            model.addAttribute("listaClientes", listaClientes);
            model.addAttribute("listaCasas", listaCasas);

            // Devolver el formulario con los datos actualizados y los errores de validación
            return "formularioReserva";
        }
    }



    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/verReservas")
    public String mostrarReservas(Model modelo) {
        List<Reserva> reservas = servicioReservas.getAllReservas();
        modelo.addAttribute("lasReservas", reservas);
        return "tablaReservas"; //JSP
    }

    @GetMapping("/editarReserva/{idR}")
    public String mostrarFormularioEditar(@PathVariable("idR") long idReserva, Model model) {
        Reserva reserva = servicioReservas.getReserva(idReserva);
        if (reserva != null) {
            List<Cliente> listaClientes = servicioClientes.getAllClientes();
            List<Casa> listaCasas = servicioCasas.getAllCasas();

            model.addAttribute("listaClientes", listaClientes);
            model.addAttribute("listaCasas", listaCasas);

            model.addAttribute("laReserva", reserva);
            return "formularioEditarReserva"; //JSP
        } else {
            return "index";
        }
    }

    @PostMapping("/editarReserva")
    public String procesarEditarReserva(@ModelAttribute("laReserva") Reserva reserva) {
        if (servicioReservas.insertarActualizarReserva(reserva)) {
            return "redirect:/verReservas";
        } else {
            throw new RuntimeException("Error al editar la reserva");
        }
    }

    @GetMapping("/eliminarReserva/{idR}")
    public String eliminarReserva(@PathVariable long idR) {
        if (servicioReservas.eliminarReserva(idR)) {
            return "redirect:/verReservas";
        } else {
            throw new RuntimeException("Error al eliminar la reserva");
        }
    }
}
