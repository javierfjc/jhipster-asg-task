package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.service.PerfilService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.PerfilDTO;
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
 * REST controller for managing Perfil.
 */
@RestController
@RequestMapping("/api")
public class PerfilResource {

    private final Logger log = LoggerFactory.getLogger(PerfilResource.class);

    private static final String ENTITY_NAME = "perfil";

    private final PerfilService perfilService;

    public PerfilResource(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    /**
     * POST  /perfils : Create a new perfil.
     *
     * @param perfilDTO the perfilDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new perfilDTO, or with status 400 (Bad Request) if the perfil has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/perfils")
    public ResponseEntity<PerfilDTO> createPerfil(@RequestBody PerfilDTO perfilDTO) throws URISyntaxException {
        log.debug("REST request to save Perfil : {}", perfilDTO);
        if (perfilDTO.getId() != null) {
            throw new BadRequestAlertException("A new perfil cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PerfilDTO result = perfilService.save(perfilDTO);
        return ResponseEntity.created(new URI("/api/perfils/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /perfils : Updates an existing perfil.
     *
     * @param perfilDTO the perfilDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated perfilDTO,
     * or with status 400 (Bad Request) if the perfilDTO is not valid,
     * or with status 500 (Internal Server Error) if the perfilDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/perfils")
    public ResponseEntity<PerfilDTO> updatePerfil(@RequestBody PerfilDTO perfilDTO) throws URISyntaxException {
        log.debug("REST request to update Perfil : {}", perfilDTO);
        if (perfilDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PerfilDTO result = perfilService.save(perfilDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, perfilDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /perfils : get all the perfils.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of perfils in body
     */
    @GetMapping("/perfils")
    public List<PerfilDTO> getAllPerfils() {
        log.debug("REST request to get all Perfils");
        return perfilService.findAll();
    }

    /**
     * GET  /perfils/:id : get the "id" perfil.
     *
     * @param id the id of the perfilDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the perfilDTO, or with status 404 (Not Found)
     */
    @GetMapping("/perfils/{id}")
    public ResponseEntity<PerfilDTO> getPerfil(@PathVariable Long id) {
        log.debug("REST request to get Perfil : {}", id);
        Optional<PerfilDTO> perfilDTO = perfilService.findOne(id);
        return ResponseUtil.wrapOrNotFound(perfilDTO);
    }

    /**
     * DELETE  /perfils/:id : delete the "id" perfil.
     *
     * @param id the id of the perfilDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/perfils/{id}")
    public ResponseEntity<Void> deletePerfil(@PathVariable Long id) {
        log.debug("REST request to delete Perfil : {}", id);
        perfilService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
