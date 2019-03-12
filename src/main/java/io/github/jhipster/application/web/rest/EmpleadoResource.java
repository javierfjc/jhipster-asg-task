package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.service.EmpleadoService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.EmpleadoDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Empleado.
 */
@RestController
@RequestMapping("/api")
public class EmpleadoResource {

    private final Logger log = LoggerFactory.getLogger(EmpleadoResource.class);

    private static final String ENTITY_NAME = "empleado";

    private final EmpleadoService empleadoService;

    public EmpleadoResource(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    /**
     * POST  /empleados : Create a new empleado.
     *
     * @param empleadoDTO the empleadoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new empleadoDTO, or with status 400 (Bad Request) if the empleado has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/empleados")
    public ResponseEntity<EmpleadoDTO> createEmpleado(@RequestBody EmpleadoDTO empleadoDTO) throws URISyntaxException {
        log.debug("REST request to save Empleado : {}", empleadoDTO);
        if (empleadoDTO.getId() != null) {
            throw new BadRequestAlertException("A new empleado cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmpleadoDTO result = empleadoService.save(empleadoDTO);
        return ResponseEntity.created(new URI("/api/empleados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /empleados : Updates an existing empleado.
     *
     * @param empleadoDTO the empleadoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated empleadoDTO,
     * or with status 400 (Bad Request) if the empleadoDTO is not valid,
     * or with status 500 (Internal Server Error) if the empleadoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/empleados")
    public ResponseEntity<EmpleadoDTO> updateEmpleado(@RequestBody EmpleadoDTO empleadoDTO) throws URISyntaxException {
        log.debug("REST request to update Empleado : {}", empleadoDTO);
        if (empleadoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmpleadoDTO result = empleadoService.save(empleadoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, empleadoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /empleados : get all the empleados.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of empleados in body
     */
    @GetMapping("/empleados")
    public ResponseEntity<List<EmpleadoDTO>> getAllEmpleados(Pageable pageable) {
        log.debug("REST request to get a page of Empleados");
        Page<EmpleadoDTO> page = empleadoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/empleados");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /empleados/:id : get the "id" empleado.
     *
     * @param id the id of the empleadoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the empleadoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/empleados/{id}")
    public ResponseEntity<EmpleadoDTO> getEmpleado(@PathVariable Long id) {
        log.debug("REST request to get Empleado : {}", id);
        Optional<EmpleadoDTO> empleadoDTO = empleadoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(empleadoDTO);
    }

    /**
     * DELETE  /empleados/:id : delete the "id" empleado.
     *
     * @param id the id of the empleadoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
        log.debug("REST request to delete Empleado : {}", id);
        empleadoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
