package com.edugo.superheroservice.controller;

import com.edugo.superheroservice.domain.Superhero;
import com.edugo.superheroservice.service.SuperheroService;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
  public @ResponseBody Collection<Superhero> getAll() {
    return superheroService.findAll();
  }
}
