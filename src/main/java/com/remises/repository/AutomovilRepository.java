package com.remises.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.remises.model.Automovil;

@Repository
public interface AutomovilRepository extends CrudRepository<Automovil, Long> {

	Automovil findTopByPatente(String patente);	

}