package com.miproyecto.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class aplicacion {
    public static void main(String[] args) {
        SpringApplication.run(aplicacion.class, args);
    }

    @GetMapping("./inicio-sesion")
    public String mostrarInicioSesion() {
        return "Inicio-sesion";
    }

}
