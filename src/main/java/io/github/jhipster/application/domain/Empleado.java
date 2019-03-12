package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * The Employee entity.
 */
@Entity
@Table(name = "empleado")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The firstname attribute.
     */
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "antiguedad")
    private Instant antiguedad;

    @Column(name = "salario")
    private Long salario;

    @Column(name = "comision")
    private Long comision;

    @ManyToOne
    @JsonIgnoreProperties("validas")
    private Tarea tarea;

    @ManyToOne
    @JsonIgnoreProperties("validas")
    private Tarea tarea;

    @ManyToOne
    @JsonIgnoreProperties("validas")
    private Tarea tarea;

    @ManyToOne
    @JsonIgnoreProperties("empleados")
    private Departamento departamento;

    @OneToMany(mappedBy = "empleado")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Perfil> perfils = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("empleados")
    private Empleado jefe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Empleado nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public Empleado email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public Empleado telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Instant getAntiguedad() {
        return antiguedad;
    }

    public Empleado antiguedad(Instant antiguedad) {
        this.antiguedad = antiguedad;
        return this;
    }

    public void setAntiguedad(Instant antiguedad) {
        this.antiguedad = antiguedad;
    }

    public Long getSalario() {
        return salario;
    }

    public Empleado salario(Long salario) {
        this.salario = salario;
        return this;
    }

    public void setSalario(Long salario) {
        this.salario = salario;
    }

    public Long getComision() {
        return comision;
    }

    public Empleado comision(Long comision) {
        this.comision = comision;
        return this;
    }

    public void setComision(Long comision) {
        this.comision = comision;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public Empleado tarea(Tarea tarea) {
        this.tarea = tarea;
        return this;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public Empleado tarea(Tarea tarea) {
        this.tarea = tarea;
        return this;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public Empleado tarea(Tarea tarea) {
        this.tarea = tarea;
        return this;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public Empleado departamento(Departamento departamento) {
        this.departamento = departamento;
        return this;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Set<Perfil> getPerfils() {
        return perfils;
    }

    public Empleado perfils(Set<Perfil> perfils) {
        this.perfils = perfils;
        return this;
    }

    public Empleado addPerfil(Perfil perfil) {
        this.perfils.add(perfil);
        perfil.setEmpleado(this);
        return this;
    }

    public Empleado removePerfil(Perfil perfil) {
        this.perfils.remove(perfil);
        perfil.setEmpleado(null);
        return this;
    }

    public void setPerfils(Set<Perfil> perfils) {
        this.perfils = perfils;
    }

    public Empleado getJefe() {
        return jefe;
    }

    public Empleado jefe(Empleado empleado) {
        this.jefe = empleado;
        return this;
    }

    public void setJefe(Empleado empleado) {
        this.jefe = empleado;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Empleado empleado = (Empleado) o;
        if (empleado.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), empleado.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Empleado{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", email='" + getEmail() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", antiguedad='" + getAntiguedad() + "'" +
            ", salario=" + getSalario() +
            ", comision=" + getComision() +
            "}";
    }
}