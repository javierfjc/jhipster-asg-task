package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.EmpleadoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Empleado and its DTO EmpleadoDTO.
 */
@Mapper(componentModel = "spring", uses = {TareaMapper.class, DepartamentoMapper.class})
public interface EmpleadoMapper extends EntityMapper<EmpleadoDTO, Empleado> {

    @Mapping(source = "tarea.id", target = "tareaId")
    @Mapping(source = "tarea.id", target = "tareaId")
    @Mapping(source = "tarea.id", target = "tareaId")
    @Mapping(source = "departamento.id", target = "departamentoId")
    @Mapping(source = "jefe.id", target = "jefeId")
    EmpleadoDTO toDto(Empleado empleado);

    @Mapping(source = "tareaId", target = "tarea")
    @Mapping(source = "tareaId", target = "tarea")
    @Mapping(source = "tareaId", target = "tarea")
    @Mapping(source = "departamentoId", target = "departamento")
    @Mapping(target = "perfils", ignore = true)
    @Mapping(source = "jefeId", target = "jefe")
    Empleado toEntity(EmpleadoDTO empleadoDTO);

    default Empleado fromId(Long id) {
        if (id == null) {
            return null;
        }
        Empleado empleado = new Empleado();
        empleado.setId(id);
        return empleado;
    }
}
