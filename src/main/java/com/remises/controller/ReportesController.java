package com.remises.controller;

import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remises.model.Chofer;
import com.remises.service.ReporteService;

@RestController
@RequestMapping("/reporte")
public class ReportesController {

	@Autowired
    private ReporteService service;
	
	private static final Logger LOGGER = Logger.getLogger(ReportesController.class);

	@GetMapping("/{id}")
    public ResponseEntity<FileOutputStream> reporte(@PathVariable("id") Long id) {
    	LOGGER.info("Creando Reporte en formato EXCEL");
        
    	FileOutputStream file = null;
    	
    	try {
    		Chofer chofer = new Chofer();
    		chofer.setId(id);
			file = service.reporte(chofer);
		} catch (Exception e) {
			LOGGER.error("ERROR al imprimir EXCEL: " + e);
		}
        return new ResponseEntity<FileOutputStream>(file, HttpStatus.OK);
    }

}
