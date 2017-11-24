package com.remises.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.remises.model.Viaje;

@Repository
public interface ViajeRepository extends CrudRepository<Viaje, Long> {
	
}
