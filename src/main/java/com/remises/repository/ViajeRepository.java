package com.remises.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.remises.model.Chofer;
import com.remises.model.Viaje;

@Repository
public interface ViajeRepository extends CrudRepository<Viaje, Long> {
	
	List<Viaje> findByChofer(Chofer chofer);
	
}
