package com.remises.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.remises.model.Ciudad;
import com.remises.repository.CiudadRepository;

@RestController
@RequestMapping("/ciudad")
public class CiudadController {

    @Autowired
    private CiudadRepository repository;
    
    private static final Logger LOGGER = Logger.getLogger(CiudadController.class);
    
    @GetMapping
    public ResponseEntity<List<Ciudad>> listAllCitys() {
		LOGGER.info("Recuperando todas las ciudades");
    	List<Ciudad> ciudades = (List<Ciudad>) this.repository.findAll();
        if (ciudades.isEmpty()) {
            return new ResponseEntity<List<Ciudad>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Ciudad>>(ciudades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> getCity(@PathVariable("id") Long id) {
        LOGGER.info("Recuperando Ciudad con id " + id);
        Ciudad ciudad = this.repository.findOne(id);
        if (ciudad == null) {
            LOGGER.error("Ciudad con id " + id + " no encontrado");
            return new ResponseEntity<Ciudad>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Ciudad>(ciudad, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createCitys(@RequestBody List<Ciudad> ciudades, UriComponentsBuilder ucBuilder) {
        LOGGER.info("Creando Ciudades");

        this.repository.save(ciudades);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
}
