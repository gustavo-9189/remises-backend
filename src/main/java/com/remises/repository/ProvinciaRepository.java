package com.remises.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.remises.model.Provincia;

@Repository
public interface ProvinciaRepository extends CrudRepository<Provincia, Long> {

}
