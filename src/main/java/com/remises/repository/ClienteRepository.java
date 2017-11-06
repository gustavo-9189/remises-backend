package com.remises.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.remises.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	Cliente findTopByDni(Integer dni);

}
