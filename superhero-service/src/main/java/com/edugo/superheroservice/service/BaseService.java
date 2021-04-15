package com.edugo.superheroservice.service;

import java.util.Collection;

public interface BaseService<T> {

  Collection<T> findAll();
}
