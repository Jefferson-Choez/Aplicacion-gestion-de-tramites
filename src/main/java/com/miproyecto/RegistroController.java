package com.miproyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("./Registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "Registro";
    }

    @PostMapping("./Registro")
    public String registrarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Registro";
        }

        // Encripta la contraseña antes de guardar
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));

        // Guarda el usuario en la base de datos
        usuarioRepository.save(usuario);

        // Redirige a la página de registro exitoso
        return "./Registro-exitoso";
    }
}
