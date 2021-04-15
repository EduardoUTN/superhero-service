package com.edugo.superheroservice.service;

import java.util.Collection;
import javax.persistence.EntityNotFoundException;

public interface BaseService<T> {

  Collection<T> findAll();

  T findById(Long id) throws EntityNotFoundException;

  T updateSuperhero(Long id, T clazz);
}
