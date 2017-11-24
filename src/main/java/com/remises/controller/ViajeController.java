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

import com.remises.model.Viaje;
import com.remises.repository.ViajeRepository;
import com.remises.response.ViajeResponse;
import com.remises.service.ViajeService;

@RestController
@RequestMapping("/viaje")
public class ViajeController {

	@Autowired
    private ViajeRepository repository;
	
	@Autowired
	private ViajeService service;
	
	private static final Logger LOGGER = Logger.getLogger(ViajeController.class);

	@GetMapping
    public ResponseEntity<List<ViajeResponse>> listAllViajes() {
    	LOGGER.info("Recuperando todos los viajes");
        List<ViajeResponse> viajes = this.service.listarViajes();
        if (viajes.isEmpty()) {
            return new ResponseEntity<List<ViajeResponse>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ViajeResponse>>(viajes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViajeResponse> getViaje(@PathVariable("id") Long id) {
        LOGGER.info("Recuperando Viaje con id " + id);
        ViajeResponse viaje = this.service.getViaje(id);
        if (viaje == null) {
            LOGGER.error("Viaje con id " + id + " no encontrado");
            return new ResponseEntity<ViajeResponse>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ViajeResponse>(viaje, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createCliente(@RequestBody Viaje viaje, UriComponentsBuilder ucBuilder) {
        LOGGER.info("Creando Viaje " + viaje.getOrigen() + " " + viaje.getDestino());

        this.repository.save(viaje);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/viaje/{id}").buildAndExpand(viaje.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viaje> updateViaje(@PathVariable("id") Long id, @RequestBody Viaje viaje) {
        LOGGER.info("Actualizando viaje " + id);

        if (this.repository.exists(id)) {
        	viaje.setId(id);
            this.repository.save(viaje);
            return new ResponseEntity<Viaje>(viaje, HttpStatus.OK);
        }
        LOGGER.error("Viaje con id " + id + " no encontrado");
        return new ResponseEntity<Viaje>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Viaje> deleteViaje(@PathVariable("id") Long id) {
        LOGGER.info("Recuperando y borrando el viaje con id " + id);

        if (this.repository.exists(id)) {
            this.repository.delete(id);
            return new ResponseEntity<Viaje>(HttpStatus.NO_CONTENT);
        }
        LOGGER.error("No se puede eliminar. Viaje con id " + id + " no encontrado");
        return new ResponseEntity<Viaje>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<Viaje> deleteAllViajes() {
        LOGGER.info("Eliminando todos los viajes");

        this.repository.deleteAll();
        return new ResponseEntity<Viaje>(HttpStatus.NO_CONTENT);
    }

}