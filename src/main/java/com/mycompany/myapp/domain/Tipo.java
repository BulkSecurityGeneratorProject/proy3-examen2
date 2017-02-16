package com.mycompany.myapp.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Tipo.
 */
@Entity
@Table(name = "tipo")
public class Tipo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private Double precio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Tipo nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public Tipo precio(Double precio) {
        this.precio = precio;
        return this;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tipo tipo = (Tipo) o;
        if (tipo.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, tipo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Tipo{" +
            "id=" + id +
            ", nombre='" + nombre + "'" +
            ", precio='" + precio + "'" +
            '}';
    }
}
