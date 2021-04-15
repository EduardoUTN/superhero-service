package com.edugo.superheroservice.controller;

import com.edugo.superheroservice.domain.Superhero;
import com.edugo.superheroservice.service.SuperheroService;
import java.util.Collection;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/superhero")
public class SuperheroController {

  private final SuperheroService superheroService;

  public SuperheroController(SuperheroService superheroService) {
    this.superheroService = superheroService;
  }

  @GetMapping
  public @ResponseBody Collection<Superhero> getAll(@RequestParam(required = false) String name) {
    if(!ObjectUtils.isEmpty(name)) {
      return superheroService.searchByName(name);
    }
    return superheroService.findAll();
  }

  @GetMapping("/{id}")
  public @ResponseBody Superhero findById(@PathVariable Long id) {
    return superheroService.findById(id);
  }

  @PutMapping("/{id}")
  public @ResponseBody Superhero updateSuperhero(@Valid @RequestBody Superhero heroUpdate, @PathVariable Long id) {
    return superheroService.updateSuperhero(id, heroUpdate);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteSuperhero(@PathVariable Long id) {
    superheroService.deleteById(id);
  }
}
