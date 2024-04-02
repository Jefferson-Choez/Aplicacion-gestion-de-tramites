package com.miproyecto;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miproyecto.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PasswordController {

    private final UserService userService;
    private final EmailService emailService;
    private final UsuarioRepository usuarioRepository;

    public PasswordController(UserService userService, EmailService emailService, UsuarioRepository usuarioRepository) {
        this.userService = userService;
        this.emailService = emailService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("./Recuperacion-contraseña")
    public String showForgotPasswordForm() {
        return "Recuperacion-contraseña";
    }

    @PostMapping(".Recuperacion-contraseña")
    public String forgotPassword(HttpServletRequest request,
                                @RequestParam("email") String email,
                                RedirectAttributes redirectAttributes) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);
        if (!optionalUsuario.isPresent()) {
            redirectAttributes.addFlashAttribute("errorMessage", "No existe una cuenta con el correo electronico proporcionado")
            return "redirect:./Recuperacion.contraseña";
        }
        Usuario usuario = optionalUsuario.get();
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(usuario, token);
        emailService.sendResetTokenEmail(getAppUrl(request), token, usuario);
        redirectAttributes.addFlashAttribute("successMessage", "Se ha enviado una clave a su correo electronico para recuperar la contraseña.");
        return "redirect:/Restablecer-contraseña";
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

    }

    @GetMapping("./Restablecer-contraseña")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        String result = userService.validatePasswordResetToken(token);
        if (result != null) {
            model.addAttribute("message", "El token para restablecer la contraseña no es válido.");
            return "redirect:./Restablecer-contraseña";
        }
        model.addAttribute("token", token);
        return "resetPasswordForm";
    }

    @PostMapping("./Inicio-sesion")
    public String processResetPasswordForm(Model model, @RequestParam("token") String token,
            @RequestParam("password") String newPassword) {
        String result = userService.validatePasswordResetToken(token);
        if (result != null) {
            model.addAttribute("message", result);
            return "redirect:./Inicio-sesion";
        }
        Usuario usuario = userService.getUserByPasswordResetToken(token);
        if (usuario != null) {
            userService.changeUserPassword(usuario, newPassword);
            return "redirect:./Inicio-sesion";
        }
        return "redirect:./Inicio-sesion";
    }
}
