package com.remises.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.remises.model.Chofer;
import com.remises.repository.ChoferRepository;

@RestController
@RequestMapping(value = "/chofer")
public class ChoferController {

    @Autowired
    private ChoferRepository repository;
    
    private static final Logger LOGGER = Logger.getLogger(ChoferController.class);

    @GetMapping
    public ResponseEntity<List<Chofer>> listAllChoferes() {
    	LOGGER.info("Recuperando todos los choferes");
        List<Chofer> choferes = (List<Chofer>) this.repository.findAll();
        if (choferes.isEmpty()) {
            return new ResponseEntity<List<Chofer>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Chofer>>(choferes, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Chofer> getChofer(@PathVariable("id") Long id) {
        LOGGER.info("Recuperando Chofer con id " + id);
        Chofer chofer = this.repository.findOne(id);
        if (chofer == null) {
            LOGGER.error("Chofer con id " + id + " no encontrado");
            return new ResponseEntity<Chofer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Chofer>(chofer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createChofer(@RequestBody Chofer chofer, UriComponentsBuilder ucBuilder) {
        LOGGER.info("Creando Chofer " + chofer.getNombre() + " " + chofer.getApellido());

        Chofer existeDni = this.repository.findTopByDni(chofer.getDni());

        if (existeDni != null) {
            LOGGER.error("Un chofer con el dni " + chofer.getDni() + " ya existe");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        this.repository.save(chofer);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/chofer/{id}").buildAndExpand(chofer.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Chofer> updateChofer(@PathVariable("id") Long id, @RequestBody Chofer chofer) {
        LOGGER.info("Actualizando chofer " + id);

        if (this.repository.exists(id)) {
        	chofer.setId(id);
            this.repository.save(chofer);
            return new ResponseEntity<Chofer>(chofer, HttpStatus.OK);
        }
        LOGGER.error("Chofer con id " + id + " no encontrado");
        return new ResponseEntity<Chofer>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Chofer> deleteChofer(@PathVariable("id") Long id) {
        LOGGER.info("Recuperando y borrando el chofer con id " + id);

        if (this.repository.exists(id)) {
            this.repository.delete(id);
            return new ResponseEntity<Chofer>(HttpStatus.NO_CONTENT);
        }
        LOGGER.error("No se puede eliminar. Chofer con id " + id + " no encontrado");
        return new ResponseEntity<Chofer>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<Chofer> deleteAllChoferes() {
        LOGGER.info("Eliminando todos los choferes");

        this.repository.deleteAll();
        return new ResponseEntity<Chofer>(HttpStatus.NO_CONTENT);
    }

}