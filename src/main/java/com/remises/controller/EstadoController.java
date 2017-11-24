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

import com.remises.model.Estado;
import com.remises.repository.EstadoRepository;

@RestController
@RequestMapping("/estado")
public class EstadoController {
	
    @Autowired
    private EstadoRepository repository;
    
    private static final Logger LOGGER = Logger.getLogger(EstadoController.class);
    
    @GetMapping
    public ResponseEntity<List<Estado>> listAllEstados() {
		LOGGER.info("Recuperando todos los Estados de los Viajes");
		List<Estado> estados = null;
		try {
	    	estados = (List<Estado>) this.repository.findAll();
	        if (estados.isEmpty()) {
	            return new ResponseEntity<List<Estado>>(HttpStatus.NO_CONTENT);
	        }
		} catch (Exception e) {
			LOGGER.error(e);
		}
        return new ResponseEntity<List<Estado>>(estados, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> getEstado(@PathVariable("id") Long id) {
        LOGGER.info("Recuperando Estado con id " + id);
        Estado estado = null;
        try {
            estado = this.repository.findOne(id);
            if (estado == null) {
                LOGGER.warn("Estado con id " + id + " no encontrado");
                return new ResponseEntity<Estado>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
			LOGGER.error(e);
		}
        return new ResponseEntity<Estado>(estado, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createEstados(@RequestBody List<Estado> estados, UriComponentsBuilder ucBuilder) {
        LOGGER.info("Creando Estados de Viajes");
        try {
            this.repository.save(estados);
		} catch (Exception e) {
			LOGGER.error(e);
		}
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}
