package es.angelu.Contolador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class ControladorLogin {
    @GetMapping("/login")
    String irLogin() {
        return "login";
    }

    @PostMapping("/login")
    String procesarLogin() {
        return "/";
    }

    @GetMapping("/logout")
    String irLogout() {
        return "logout";
    }

    @PostMapping("/logout")
    String procesarLogout() {
        return "/";
    }
}
