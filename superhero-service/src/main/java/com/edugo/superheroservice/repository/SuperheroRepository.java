package com.edugo.superheroservice.repository;

import com.edugo.superheroservice.domain.Superhero;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, Long> {

  List<Superhero> findByNameContainingIgnoreCase(String name);
}
