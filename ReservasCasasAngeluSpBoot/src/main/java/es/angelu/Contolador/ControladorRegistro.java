package es.angelu.Contolador;

import es.angelu.Entities.Usuario;
import es.angelu.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/registro")
@Controller
public class ControladorRegistro {

    @Autowired  //Se inyecta el bean
    private UsuarioRepository usuarioRepository;
    @Autowired  //Se inyecta el Bean para encriptar la password
    private PasswordEncoder passwordEncoder;
    @Autowired
    Usuario usuario;

    @GetMapping("/formulario")
    public String mostrarFormularioRegistro(Model modelo) {
        modelo.addAttribute("elUsuario",usuario );
        return "formularioRegistro";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute ("elUsuario") Usuario usuario) {
        //se encripta la password
        String passwordEncriptada = passwordEncoder.encode(usuario.getPassword());
        //usuario nuevo

        usuario.setNombreUsuario(usuario.getNombreUsuario());
        usuario.setPassword(passwordEncriptada);
        usuario.setRol(usuario.getRol());
        usuarioRepository.save(usuario);
        return "login"; // login después de guardar
    }
}
