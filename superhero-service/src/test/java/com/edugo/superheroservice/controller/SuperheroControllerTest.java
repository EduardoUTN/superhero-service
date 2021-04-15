package com.edugo.superheroservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.edugo.superheroservice.domain.Superhero;
import com.edugo.superheroservice.service.SuperheroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
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
  private Superhero hero;
  private Superhero anotherHero;

  @BeforeEach
  void setUp() {
    JacksonTester.initFields(this, new ObjectMapper());
    mvc = MockMvcBuilders.standaloneSetup(superheroController)
          .build();

    hero = Superhero.builder().id(1L).name("Superman").build();
    anotherHero = Superhero.builder().id(2L).name("The Flash").build();
  }

  @Test
  void shouldReturnSuperheroesResponseWhenExists() throws Exception {
    Collection<Superhero> heroes = Arrays.asList(hero, anotherHero);
    given(superheroService.findAll()).willReturn(heroes);

    MockHttpServletResponse response = mvc
        .perform(get("/superhero"))
        .andReturn().getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo(collectionJsonSuperHero.write(heroes).getJson());
  }
}