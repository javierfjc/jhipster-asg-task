package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.service.ContactoService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.ContactoDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Contacto.
 */
@RestController
@RequestMapping("/api")
public class ContactoResource {

    private final Logger log = LoggerFactory.getLogger(ContactoResource.class);

    private static final String ENTITY_NAME = "contacto";

    private final ContactoService contactoService;

    public ContactoResource(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    /**
     * POST  /contactos : Create a new contacto.
     *
     * @param contactoDTO the contactoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contactoDTO, or with status 400 (Bad Request) if the contacto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/contactos")
    public ResponseEntity<ContactoDTO> createContacto(@RequestBody ContactoDTO contactoDTO) throws URISyntaxException {
        log.debug("REST request to save Contacto : {}", contactoDTO);
        if (contactoDTO.getId() != null) {
            throw new BadRequestAlertException("A new contacto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContactoDTO result = contactoService.save(contactoDTO);
        return ResponseEntity.created(new URI("/api/contactos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /contactos : Updates an existing contacto.
     *
     * @param contactoDTO the contactoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contactoDTO,
     * or with status 400 (Bad Request) if the contactoDTO is not valid,
     * or with status 500 (Internal Server Error) if the contactoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/contactos")
    public ResponseEntity<ContactoDTO> updateContacto(@RequestBody ContactoDTO contactoDTO) throws URISyntaxException {
        log.debug("REST request to update Contacto : {}", contactoDTO);
        if (contactoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContactoDTO result = contactoService.save(contactoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, contactoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /contactos : get all the contactos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of contactos in body
     */
    @GetMapping("/contactos")
    public List<ContactoDTO> getAllContactos() {
        log.debug("REST request to get all Contactos");
        return contactoService.findAll();
    }

    /**
     * GET  /contactos/:id : get the "id" contacto.
     *
     * @param id the id of the contactoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contactoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/contactos/{id}")
    public ResponseEntity<ContactoDTO> getContacto(@PathVariable Long id) {
        log.debug("REST request to get Contacto : {}", id);
        Optional<ContactoDTO> contactoDTO = contactoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contactoDTO);
    }

    /**
     * DELETE  /contactos/:id : delete the "id" contacto.
     *
     * @param id the id of the contactoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/contactos/{id}")
    public ResponseEntity<Void> deleteContacto(@PathVariable Long id) {
        log.debug("REST request to delete Contacto : {}", id);
        contactoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
