package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.TareaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Tarea and its DTO TareaDTO.
 */
@Mapper(componentModel = "spring", uses = {ProyectoMapper.class, ContactoMapper.class})
public interface TareaMapper extends EntityMapper<TareaDTO, Tarea> {

    @Mapping(source = "proyecto.id", target = "proyectoId")
    @Mapping(source = "contacto.id", target = "contactoId")
    @Mapping(source = "tarea.id", target = "tareaId")
    @Mapping(source = "tarea.id", target = "tareaId")
    TareaDTO toDto(Tarea tarea);

    @Mapping(source = "proyectoId", target = "proyecto")
    @Mapping(source = "contactoId", target = "contacto")
    @Mapping(target = "creas", ignore = true)
    @Mapping(target = "asignados", ignore = true)
    @Mapping(target = "validas", ignore = true)
    @Mapping(source = "tareaId", target = "tarea")
    @Mapping(target = "maestras", ignore = true)
    @Mapping(source = "tareaId", target = "tarea")
    @Mapping(target = "esperas", ignore = true)
    Tarea toEntity(TareaDTO tareaDTO);

    default Tarea fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tarea tarea = new Tarea();
        tarea.setId(id);
        return tarea;
    }
}
