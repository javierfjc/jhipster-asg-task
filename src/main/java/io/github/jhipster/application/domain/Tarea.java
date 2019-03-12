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

import io.github.jhipster.application.domain.enumeration.TareasEstado;

/**
 * A Tarea.
 */
@Entity
@Table(name = "tarea")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tarea implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private TareasEstado estado;

    @Column(name = "fecha_creado")
    private Instant fechaCreado;

    @Column(name = "fecha_previsto_inicio")
    private Instant fechaPrevistoInicio;

    @Column(name = "fecha_inicio")
    private Instant fechaInicio;

    @Column(name = "fecha_final")
    private Instant fechaFinal;

    @Column(name = "horas_previsto")
    private Integer horasPrevisto;

    @ManyToOne
    @JsonIgnoreProperties("tareas")
    private Proyecto proyecto;

    @ManyToOne
    @JsonIgnoreProperties("tareas")
    private Contacto contacto;

    @OneToMany(mappedBy = "tarea")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Empleado> creas = new HashSet<>();
    @OneToMany(mappedBy = "tarea")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Empleado> asignados = new HashSet<>();
    @OneToMany(mappedBy = "tarea")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Empleado> validas = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("esperas")
    private Tarea tarea;

    @OneToMany(mappedBy = "tarea")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tarea> maestras = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("esperas")
    private Tarea tarea;

    @OneToMany(mappedBy = "tarea")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tarea> esperas = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Tarea descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TareasEstado getEstado() {
        return estado;
    }

    public Tarea estado(TareasEstado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(TareasEstado estado) {
        this.estado = estado;
    }

    public Instant getFechaCreado() {
        return fechaCreado;
    }

    public Tarea fechaCreado(Instant fechaCreado) {
        this.fechaCreado = fechaCreado;
        return this;
    }

    public void setFechaCreado(Instant fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public Instant getFechaPrevistoInicio() {
        return fechaPrevistoInicio;
    }

    public Tarea fechaPrevistoInicio(Instant fechaPrevistoInicio) {
        this.fechaPrevistoInicio = fechaPrevistoInicio;
        return this;
    }

    public void setFechaPrevistoInicio(Instant fechaPrevistoInicio) {
        this.fechaPrevistoInicio = fechaPrevistoInicio;
    }

    public Instant getFechaInicio() {
        return fechaInicio;
    }

    public Tarea fechaInicio(Instant fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    public void setFechaInicio(Instant fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Instant getFechaFinal() {
        return fechaFinal;
    }

    public Tarea fechaFinal(Instant fechaFinal) {
        this.fechaFinal = fechaFinal;
        return this;
    }

    public void setFechaFinal(Instant fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Integer getHorasPrevisto() {
        return horasPrevisto;
    }

    public Tarea horasPrevisto(Integer horasPrevisto) {
        this.horasPrevisto = horasPrevisto;
        return this;
    }

    public void setHorasPrevisto(Integer horasPrevisto) {
        this.horasPrevisto = horasPrevisto;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public Tarea proyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
        return this;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public Tarea contacto(Contacto contacto) {
        this.contacto = contacto;
        return this;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public Set<Empleado> getCreas() {
        return creas;
    }

    public Tarea creas(Set<Empleado> empleados) {
        this.creas = empleados;
        return this;
    }

    public Tarea addCrea(Empleado empleado) {
        this.creas.add(empleado);
        empleado.setTarea(this);
        return this;
    }

    public Tarea removeCrea(Empleado empleado) {
        this.creas.remove(empleado);
        empleado.setTarea(null);
        return this;
    }

    public void setCreas(Set<Empleado> empleados) {
        this.creas = empleados;
    }

    public Set<Empleado> getAsignados() {
        return asignados;
    }

    public Tarea asignados(Set<Empleado> empleados) {
        this.asignados = empleados;
        return this;
    }

    public Tarea addAsignado(Empleado empleado) {
        this.asignados.add(empleado);
        empleado.setTarea(this);
        return this;
    }

    public Tarea removeAsignado(Empleado empleado) {
        this.asignados.remove(empleado);
        empleado.setTarea(null);
        return this;
    }

    public void setAsignados(Set<Empleado> empleados) {
        this.asignados = empleados;
    }

    public Set<Empleado> getValidas() {
        return validas;
    }

    public Tarea validas(Set<Empleado> empleados) {
        this.validas = empleados;
        return this;
    }

    public Tarea addValida(Empleado empleado) {
        this.validas.add(empleado);
        empleado.setTarea(this);
        return this;
    }

    public Tarea removeValida(Empleado empleado) {
        this.validas.remove(empleado);
        empleado.setTarea(null);
        return this;
    }

    public void setValidas(Set<Empleado> empleados) {
        this.validas = empleados;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public Tarea tarea(Tarea tarea) {
        this.tarea = tarea;
        return this;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public Set<Tarea> getMaestras() {
        return maestras;
    }

    public Tarea maestras(Set<Tarea> tareas) {
        this.maestras = tareas;
        return this;
    }

    public Tarea addMaestra(Tarea tarea) {
        this.maestras.add(tarea);
        tarea.setTarea(this);
        return this;
    }

    public Tarea removeMaestra(Tarea tarea) {
        this.maestras.remove(tarea);
        tarea.setTarea(null);
        return this;
    }

    public void setMaestras(Set<Tarea> tareas) {
        this.maestras = tareas;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public Tarea tarea(Tarea tarea) {
        this.tarea = tarea;
        return this;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public Set<Tarea> getEsperas() {
        return esperas;
    }

    public Tarea esperas(Set<Tarea> tareas) {
        this.esperas = tareas;
        return this;
    }

    public Tarea addEspera(Tarea tarea) {
        this.esperas.add(tarea);
        tarea.setTarea(this);
        return this;
    }

    public Tarea removeEspera(Tarea tarea) {
        this.esperas.remove(tarea);
        tarea.setTarea(null);
        return this;
    }

    public void setEsperas(Set<Tarea> tareas) {
        this.esperas = tareas;
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
        Tarea tarea = (Tarea) o;
        if (tarea.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tarea.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tarea{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            ", estado='" + getEstado() + "'" +
            ", fechaCreado='" + getFechaCreado() + "'" +
            ", fechaPrevistoInicio='" + getFechaPrevistoInicio() + "'" +
            ", fechaInicio='" + getFechaInicio() + "'" +
            ", fechaFinal='" + getFechaFinal() + "'" +
            ", horasPrevisto=" + getHorasPrevisto() +
            "}";
    }
}
