package com.miproyecto.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.miproyecto.Usuario;
import com.miproyecto.UsuarioRepository;

@Service
public class UsuarioRegistro {

    private final UsuarioRepository usuarioRepository;

    public UsuarioRegistro(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean autenticarUsuario(String email, String password) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            if (usuario.getContraseña().equals(password)) {

                return true;
            }
        }
        return false;
    }
}