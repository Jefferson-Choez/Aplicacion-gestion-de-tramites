package com.miproyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miproyecto.services.AuthService;

@Controller
public class AuthController {

    @Autowired
    private AuthService AuthService;

    @GetMapping("./Panel-usuario")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/inicio-sesion")
    public String iniciarSesion(
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        boolean autenticacionExitosa = AuthService.autenticarUsuario(email, password);
        if (autenticacionExitosa) {
            return "redirect:/Panel-usuario";
        } else {
            model.addAttribute("error", "Credenciales inválidas");
            return "Inicio-sesion";
        }
    }
}
