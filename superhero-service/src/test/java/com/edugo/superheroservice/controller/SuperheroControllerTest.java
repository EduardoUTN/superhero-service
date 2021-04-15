package com.edugo.superheroservice.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.edugo.superheroservice.api.ApiError;
import com.edugo.superheroservice.api.RestExceptionHandler;
import com.edugo.superheroservice.domain.Superhero;
import com.edugo.superheroservice.service.SuperheroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Collection;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class SuperheroControllerTest {

  private MockMvc mvc;

  @Mock
  private SuperheroService superheroService;

  @InjectMocks
  private SuperheroController superheroController;

  private JacksonTester<Superhero> jsonSuperHero;
  private JacksonTester<Collection<Superhero>> collectionJsonSuperHero;
  private JacksonTester<ApiError> jsonApiError;
  private Superhero hero;
  private Superhero anotherHero;

  @BeforeEach
  void setUp() {
    JacksonTester.initFields(this, new ObjectMapper());
    mvc = MockMvcBuilders.standaloneSetup(superheroController)
        .setControllerAdvice(new RestExceptionHandler())
        .build();

    hero = Superhero.builder().id(1L).name("Superman").build();
    anotherHero = Superhero.builder().id(2L).name("The Flash").build();
  }

  @Test
  void shouldReturnSuperheroesResponseWhenExists() throws Exception {
    Collection<Superhero> heroes = Arrays.asList(hero, anotherHero);
    given(superheroService.findAll()).willReturn(heroes);

    final ResultActions response = mvc
        .perform(get("/superhero"));

    response.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(collectionJsonSuperHero.write(heroes).getJson()));
  }

  @Test
  void shouldReturnSuperheroResponseWhenIdExists() throws Exception {
    given(superheroService.findById(1L)).willReturn(hero);

    final ResultActions response = mvc
        .perform(get("/superhero/1"));

    response.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(jsonSuperHero.write(hero).getJson()));
  }

  @Test
  void shouldApiErrorResponseWhenSuperheroIdExists() throws Exception {
    Long id = 1L;
    String errorMessage = String.format("Superhero with id: %s - Not Found", id);

    given(superheroService.findById(id))
        .willThrow(new EntityNotFoundException(String.format("Superhero with id: %s - Not Found", id)));

    final ResultActions response = mvc.perform(get("/superhero/" + id));


    response.andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message", is(errorMessage)));
  }
}