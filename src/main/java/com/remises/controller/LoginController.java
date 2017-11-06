package com.remises.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remises.model.Usuario;
import com.remises.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private UsuarioRepository repository;
	
	private static final Logger LOGGER = Logger.getLogger(ReportesController.class);
	
	@PostMapping
	public ResponseEntity<Usuario> iniciarSesion(@RequestBody Usuario login) {
        LOGGER.info("Iniciando Sesion " + login.getUsuario());

        Usuario existe = this.repository.findTopByUsuarioAndClave(login.getUsuario(), login.getClave());

        if (existe == null) {
            LOGGER.warn("El usuario ".concat(login.getUsuario().concat(" no esta registrado")));
            return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Usuario>(existe, HttpStatus.OK);
    }

}
