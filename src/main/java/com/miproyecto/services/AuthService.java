package com.miproyecto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.miproyecto.Usuario;
import com.miproyecto.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean autenticarUsuario(String email, String password) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            if (passwordEncoder.matches(password, usuario.getContraseña())) {
                // La contraseña coincide
                return true;
            }
        }
        return false;
    }

    public void registrarUsuario(Usuario usuario) {
        String encodedPassword = passwordEncoder.encode(usuario.getContraseña());
        usuario.setContraseña(encodedPassword);
        usuarioRepository.save(usuario);
    }
}
