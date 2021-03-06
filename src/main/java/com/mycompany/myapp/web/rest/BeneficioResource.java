package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Beneficio;

import com.mycompany.myapp.repository.BeneficioRepository;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
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
 * REST controller for managing Beneficio.
 */
@RestController
@RequestMapping("/api")
public class BeneficioResource {

    private final Logger log = LoggerFactory.getLogger(BeneficioResource.class);

    private static final String ENTITY_NAME = "beneficio";
        
    private final BeneficioRepository beneficioRepository;

    public BeneficioResource(BeneficioRepository beneficioRepository) {
        this.beneficioRepository = beneficioRepository;
    }

    /**
     * POST  /beneficios : Create a new beneficio.
     *
     * @param beneficio the beneficio to create
     * @return the ResponseEntity with status 201 (Created) and with body the new beneficio, or with status 400 (Bad Request) if the beneficio has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/beneficios")
    @Timed
    public ResponseEntity<Beneficio> createBeneficio(@RequestBody Beneficio beneficio) throws URISyntaxException {
        log.debug("REST request to save Beneficio : {}", beneficio);
        if (beneficio.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new beneficio cannot already have an ID")).body(null);
        }
        Beneficio result = beneficioRepository.save(beneficio);
        return ResponseEntity.created(new URI("/api/beneficios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /beneficios : Updates an existing beneficio.
     *
     * @param beneficio the beneficio to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated beneficio,
     * or with status 400 (Bad Request) if the beneficio is not valid,
     * or with status 500 (Internal Server Error) if the beneficio couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/beneficios")
    @Timed
    public ResponseEntity<Beneficio> updateBeneficio(@RequestBody Beneficio beneficio) throws URISyntaxException {
        log.debug("REST request to update Beneficio : {}", beneficio);
        if (beneficio.getId() == null) {
            return createBeneficio(beneficio);
        }
        Beneficio result = beneficioRepository.save(beneficio);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, beneficio.getId().toString()))
            .body(result);
    }

    /**
     * GET  /beneficios : get all the beneficios.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of beneficios in body
     */
    @GetMapping("/beneficios")
    @Timed
    public List<Beneficio> getAllBeneficios() {
        log.debug("REST request to get all Beneficios");
        List<Beneficio> beneficios = beneficioRepository.findAll();
        return beneficios;
    }

    /**
     * GET  /beneficios/:id : get the "id" beneficio.
     *
     * @param id the id of the beneficio to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the beneficio, or with status 404 (Not Found)
     */
    @GetMapping("/beneficios/{id}")
    @Timed
    public ResponseEntity<Beneficio> getBeneficio(@PathVariable Long id) {
        log.debug("REST request to get Beneficio : {}", id);
        Beneficio beneficio = beneficioRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(beneficio));
    }

    /**
     * DELETE  /beneficios/:id : delete the "id" beneficio.
     *
     * @param id the id of the beneficio to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/beneficios/{id}")
    @Timed
    public ResponseEntity<Void> deleteBeneficio(@PathVariable Long id) {
        log.debug("REST request to delete Beneficio : {}", id);
        beneficioRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
