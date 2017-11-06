package com.remises.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.remises.model.Ciudad;

@Repository
public interface CiudadRepository extends CrudRepository<Ciudad, Long> {

}
