package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ContactoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Contacto and its DTO ContactoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ContactoMapper extends EntityMapper<ContactoDTO, Contacto> {



    default Contacto fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contacto contacto = new Contacto();
        contacto.setId(id);
        return contacto;
    }
}
