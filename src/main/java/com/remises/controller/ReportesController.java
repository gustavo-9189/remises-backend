package com.remises.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remises.model.Viaje;
import com.remises.repository.ViajeRepository;

@RestController
@RequestMapping(value = "/reporte")
public class ReportesController {

	@Autowired
    private ViajeRepository repository;
	
	private static final Logger LOGGER = Logger.getLogger(ReportesController.class);

	@GetMapping
    public ResponseEntity<List<Viaje>> listAllViajes() {
    	LOGGER.info("Recuperando todos los viajes");
        List<Viaje> viajes = (List<Viaje>) this.repository.findAll();
        if (viajes.isEmpty()) {
            return new ResponseEntity<List<Viaje>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Viaje>>(viajes, HttpStatus.OK);
    }

}
