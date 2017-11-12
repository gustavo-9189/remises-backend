package com.remises.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.remises.model.Estado;

@Repository
public interface EstadoRepository  extends CrudRepository<Estado, Long> {

}
