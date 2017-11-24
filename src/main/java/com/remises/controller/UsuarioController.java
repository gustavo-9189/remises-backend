package com.remises.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remises.model.Usuario;
import com.remises.repository.UsuarioRepository;

@RestController
@RequestMapping("/login")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	
	private static final Logger LOGGER = Logger.getLogger(ReportesController.class);
	
	@PostMapping
	public ResponseEntity<Usuario> iniciarSesion(@RequestBody Usuario login) {
        LOGGER.info("Iniciando Sesion " + login.getUsuario());

        Usuario existe = this.repository.findTopByUsuarioAndClave(login.getUsuario(), login.getClave());

        if (existe == null) {
            LOGGER.warn("El usuario ".concat(login.getUsuario().concat(" no esta registrado")));
            return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Usuario>(existe, HttpStatus.OK);
    }
	
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        LOGGER.info("Actualizando usuario " + id);

        if (this.repository.exists(id)) {
        	usuario.setId(id);
            this.repository.save(usuario);
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        }
        LOGGER.error("Chofer con id " + id + " no encontrado");
        return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
    }
	
    @GetMapping
    public ResponseEntity<List<Usuario>> listAllUsuarios() {
    	LOGGER.info("Recuperando todos los usuarios");
        List<Usuario> usuarios = (List<Usuario>) this.repository.findAll();
        if (usuarios.isEmpty()) {
            return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }
	
	@PostMapping("/grabar")
	public ResponseEntity<Usuario> grabar(@RequestBody Usuario login) {
        LOGGER.info("Iniciando Sesion " + login.getUsuario());

        this.repository.save(login);

        return new ResponseEntity<Usuario>(HttpStatus.OK);
    }

}
