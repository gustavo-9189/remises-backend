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

import com.remises.model.Cliente;
import com.remises.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
    private ClienteRepository repository;
	
	private static final Logger LOGGER = Logger.getLogger(ClienteController.class);

    @GetMapping
    public ResponseEntity<List<Cliente>> listAllClientes() {
    	LOGGER.info("Recuperando todos los clientes");
        List<Cliente> clientes = (List<Cliente>) this.repository.findAll();
        if (clientes.isEmpty()) {
            return new ResponseEntity<List<Cliente>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable("id") Long id) {
        LOGGER.info("Recuperando Cliente con id " + id);
        Cliente cliente = this.repository.findOne(id);
        if (cliente == null) {
            LOGGER.error("Cliente con id " + id + " no encontrado");
            return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createCliente(@RequestBody Cliente cliente, UriComponentsBuilder ucBuilder) {
        LOGGER.info("Creando Cliente " + cliente.getNombre() + " " + cliente.getApellido());

        Cliente existeDni = this.repository.findTopByDni(cliente.getDni());

        if (existeDni != null) {
            LOGGER.error("Un cliente con el dni " + cliente.getDni() + " ya existe");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        this.repository.save(cliente);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri());
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
        LOGGER.info("Actualizando cliente " + id);

        if (this.repository.exists(id)) {
        	cliente.setId(id);
            this.repository.save(cliente);
            return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
        }
        LOGGER.error("Cliente con id " + id + " no encontrado");
        return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable("id") Long id) {
        LOGGER.info("Recuperando y borrando el cliente con id " + id);

        if (this.repository.exists(id)) {
            this.repository.delete(id);
            return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
        }
        LOGGER.error("No se puede eliminar. Cliente con id " + id + " no encontrado");
        return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<Cliente> deleteAllClientes() {
        LOGGER.info("Eliminando todos los clientes");

        this.repository.deleteAll();
        return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
    }

}