package com.edugo.superheroservice.service;

import com.edugo.superheroservice.domain.Superhero;
import com.edugo.superheroservice.repository.SuperheroRepository;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class SuperheroService implements BaseService<Superhero> {

  private final SuperheroRepository superheroRepository;

  public SuperheroService(SuperheroRepository superheroRepository) {
    this.superheroRepository = superheroRepository;
  }

  @Override
  public Collection<Superhero> findAll() {
    return superheroRepository.findAll();
  }
}
