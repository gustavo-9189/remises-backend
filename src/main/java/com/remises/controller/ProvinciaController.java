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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.remises.model.Provincia;
import com.remises.repository.ProvinciaRepository;

@RestController
@RequestMapping(value = "/provincia")
public class ProvinciaController {

    @Autowired
    private ProvinciaRepository repository;
    
    private static final Logger LOGGER = Logger.getLogger(ProvinciaController.class);
    
    @GetMapping
    public ResponseEntity<List<Provincia>> listAllProvincias() {
		LOGGER.info("Recuperando todas las provincias");
		List<Provincia> provincias = null;
		try {
	    	provincias = (List<Provincia>) this.repository.findAll();
	        if (provincias.isEmpty()) {
	            return new ResponseEntity<List<Provincia>>(HttpStatus.NO_CONTENT);
	        }			
		} catch (Exception e) {
			LOGGER.error(e);
		}
        return new ResponseEntity<List<Provincia>>(provincias, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Provincia> getProvincia(@PathVariable("id") Long id) {
        LOGGER.info("Recuperando Provincia con id " + id);
        Provincia provincia = null;
        try {
            provincia = this.repository.findOne(id);
            if (provincia == null) {
                LOGGER.warn("Provincia con id " + id + " no encontrado");
                return new ResponseEntity<Provincia>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
			LOGGER.error(e);
		}
        return new ResponseEntity<Provincia>(provincia, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createProvincias(@RequestBody List<Provincia> provincias, UriComponentsBuilder ucBuilder) {
        LOGGER.info("Creando Provincias");
        try {
            this.repository.save(provincias);
		} catch (Exception e) {
			LOGGER.error(e);
		}
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
}
