package es.angelu.Contolador;

import es.angelu.Entities.Casa;
import es.angelu.Repository.CasasDAO;
import es.angelu.Servicios.ServicioCasas;
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
public class controladorCasas {
    @Autowired
    ServicioCasas servicioCasas;
    CasasDAO dao;
    String usuario;
    String rol;

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/agregarCasa")
    public String agregarCasa(Model modelo) {
        modelo.addAttribute("laCasa", new Casa());
        //if (usuario.equals("admin"))
            return "formularioCasa"; //JSP
        //else return "redirect:/login";
    }
    @PostMapping("/agregarCasa")
    public String procesarAgregarCasa(@ModelAttribute("laCasa") @Valid Casa casa, BindingResult result) {
        if (!result.hasErrors()) {
            if (servicioCasas.insertarActualizarCasa(casa)) {
                return "redirect:/verCasas";
            } else {
                throw new RuntimeException("Error al guardar la casa");
            }
        } else {
            return "formularioCasa"; //JSP
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')" )
    @GetMapping("/verCasas")
    public String mostrarCasas(Model modelo) {
        List<Casa> casas = servicioCasas.getAllCasas();
        modelo.addAttribute("lasCasas", casas);
        return "tablaCasas"; //JSP
    }

    @GetMapping("/editarCasa/{idC}")
    public String mostrarFormularioEditar(@PathVariable("idC") long idCasa, Model model) {
        Casa casa = servicioCasas.getCasa(idCasa);
        if (casa != null) {
            model.addAttribute("laCasa", casa);
            return "formularioEditarCasa"; //JSP
        } else {
            throw new RuntimeException("Error en la edición de la casa");
        }
    }

    @PostMapping("/editarCasa")
    public String procesarEditarCasa(@ModelAttribute("laCasa") Casa casa) {
        if (servicioCasas.actualizaCasa(casa)) {
            return "redirect:/verCasas";
        } else {
            throw new RuntimeException("Error al editar la reserva");
        }
    }

    @GetMapping("/eliminarCasa/{id}")
    public String eliminarCasa(@PathVariable long id) {
        if (servicioCasas.eliminarCasa(id)) {
            return "redirect:/verCasas";
        } else {
            throw new RuntimeException("Error al eliminar la casa");
        }
    }
}
