package com.miproyecto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El primer nombre no puede estar vacío.")
    private String primerNombre;

    @NotBlank(message = "El segundo nombre no puede estar vacío.")
    private String segundoNombre;

    @NotBlank(message = "El primer apellido no puede estar vacío.")
    private String primerApellido;

    @NotBlank(message = "El segundo apellido no puede estar vacío.")
    private String segundoApellido;

    @NotBlank(message = "La cedula no puede estar vacía.")
    private String cedula;

    @NotBlank(message = "La direccion no puede estar vacía.")
    private String direccion;

    @NotBlank(message = "La parroquia no puede estar vacía.")
    private String parroquia;

    @Email(message = "Correo electrónico no válido.")
    @NotBlank(message = "El correo electrónico no puede estar vacío.")
    private String correoElectronico;

    @NotBlank(message = "El telefono no puede estar vacío.")
    private String celular;

    @NotNull(message = "La fecha de naciemiento no puede estar vacía.")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "La contraseña no puede estar vacía.")
    private String contraseña;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public Usuario() {

    }

    public Usuario(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
            String cedula, String direccion, String parroquia, String correoElectronico, String celular,
            LocalDate fechaNacimiento, String contraseña) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.cedula = cedula;
        this.direccion = direccion;
        this.parroquia = parroquia;
        this.correoElectronico = correoElectronico;
        this.celular = celular;
        this.fechaNacimiento = fechaNacimiento;
        this.contraseña = contraseña;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getParroquia() {
        return parroquia;
    }

    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}
