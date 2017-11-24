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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.remises.model.Automovil;
import com.remises.repository.AutomovilRepository;

@RestController
@RequestMapping("/automovil")
public class AutomovilController {

    @Autowired
    private AutomovilRepository repository;
    
    private static final Logger LOGGER = Logger.getLogger(AutomovilController.class);
    
    @GetMapping
    public ResponseEntity<List<Automovil>> listAllAutomoviles() {
		LOGGER.info("Recuperando todos los automoviles");
    	List<Automovil> autos = (List<Automovil>) this.repository.findAll();
        if (autos.isEmpty()) {
            return new ResponseEntity<List<Automovil>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Automovil>>(autos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Automovil> getAutomovil(@PathVariable("id") Long id) {
    	LOGGER.info("Recuperando Automovil con id " + id);
        Automovil auto = this.repository.findOne(id);
        if (auto == null) {
            LOGGER.error("Automovil con id " + id + " no encontrado");
            return new ResponseEntity<Automovil>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Automovil>(auto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createAutomovil(@RequestBody Automovil auto, UriComponentsBuilder ucBuilder) {
        LOGGER.info("Creando Automovil " + auto.getMarca() + " " + auto.getModelo());

        Automovil existePatente = this.repository.findTopByPatente(auto.getPatente());

        if (existePatente != null) {
            LOGGER.error("Un automovil con la patente " + auto.getPatente() + " ya existe");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        this.repository.save(auto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/automovil/{id}").buildAndExpand(auto.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Automovil> updateAutomovil(@PathVariable("id") Long id, @RequestBody Automovil auto) {
        LOGGER.info("Actualizando automovil " + id);

        if (this.repository.exists(id)) {
        	auto.setId(id);
            this.repository.save(auto);
            return new ResponseEntity<Automovil>(auto, HttpStatus.OK);
        }
        LOGGER.error("Automovil con id " + id + " no encontrado");
        return new ResponseEntity<Automovil>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Automovil> deleteAutomovil(@PathVariable("id") Long id) {
        LOGGER.info("Recuperando y borrando el automovil con id " + id);

        if (this.repository.exists(id)) {
            this.repository.delete(id);
            return new ResponseEntity<Automovil>(HttpStatus.NO_CONTENT);
        }
        LOGGER.error("No se puede eliminar. Automovil con id " + id + " no encontrado");
        return new ResponseEntity<Automovil>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<Automovil> deleteAllAutomoviles() {
        LOGGER.info("Eliminando todos los automoviles");

        this.repository.deleteAll();
        return new ResponseEntity<Automovil>(HttpStatus.NO_CONTENT);
    }

}