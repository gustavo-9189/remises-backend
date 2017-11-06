package com.remises.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.remises.model.Chofer;

@Repository
public interface ChoferRepository extends CrudRepository<Chofer, Long> {

	Chofer findTopByDni(Integer dni);

}
