package com.miproyecto;

import java.util.Calendar;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Usuario usuario;

    public void setToken(String token2) {
        throw new UnsupportedOperationException("Unimplemented method 'setToken'");
    }

    public void setExpiryDate() {
        throw new UnsupportedOperationException("Unimplemented method 'setExpiryDate'");
    }

    public void setUsuario(Usuario usuario2) {
        throw new UnsupportedOperationException("Unimplemented method 'setUsuario'");
    }

    public Calendar getExpiryDate() {
        throw new UnsupportedOperationException("Unimplemented method 'getExpiryDate'");
    }

}
