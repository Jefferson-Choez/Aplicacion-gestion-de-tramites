package com.miproyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.miproyecto.PasswordResetToken;
import com.miproyecto.PasswordResetTokenRepository;
import com.miproyecto.Usuario;

@Service
public class UserService {

    @Autowired
    private final PasswordResetTokenRepository tokenRepository;

    public UserService(PasswordResetTokenRepository tokenRepository, JavaMailSender mailSender) {
        this.tokenRepository = tokenRepository;
    }

    public void createPasswordResetTokenForUser(Usuario usuario, String token) {
        PasswordResetToken myToken = new PasswordResetToken();
        myToken.setUsuario(usuario);
        myToken.setToken(token);
        myToken.setExpiryDate();

        tokenRepository.save(myToken);

        String url = constructResetTokenUrl(token);
        sendPasswordResetEmail(usuario.getCorreoElectronico(), url);
    }

    public String constructResetTokenUrl(String token) {
        return "http://yourapp.com/reset-password?token=" + token;
    }

    public void sendPasswordResetEmail(String to, String token) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject("Recuperación de contraseña");
        email.setText("Para restablecer tu contraseña, haz clic en el siguiente enlace: "
                + "http://yourapp.com/reset-password?token=" + token);
    }

    public String validatePasswordResetToken(String token) {
        throw new UnsupportedOperationException("Unimplemented method 'validatePasswordResetToken'");
    }

    public Usuario getUserByPasswordResetToken(String token) {
        throw new UnsupportedOperationException("Unimplemented method 'getUserByPasswordResetToken'");
    }

    public void changeUserPassword(Usuario usuario, String newPassword) {
        throw new UnsupportedOperationException("Unimplemented method 'changeUserPassword'");
    }

}