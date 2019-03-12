package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PerfilDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Perfil and its DTO PerfilDTO.
 */
@Mapper(componentModel = "spring", uses = {EmpleadoMapper.class})
public interface PerfilMapper extends EntityMapper<PerfilDTO, Perfil> {

    @Mapping(source = "empleado.id", target = "empleadoId")
    PerfilDTO toDto(Perfil perfil);

    @Mapping(source = "empleadoId", target = "empleado")
    Perfil toEntity(PerfilDTO perfilDTO);

    default Perfil fromId(Long id) {
        if (id == null) {
            return null;
        }
        Perfil perfil = new Perfil();
        perfil.setId(id);
        return perfil;
    }
}
