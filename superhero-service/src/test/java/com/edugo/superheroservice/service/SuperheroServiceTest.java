package com.edugo.superheroservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.edugo.superheroservice.domain.Superhero;
import com.edugo.superheroservice.repository.SuperheroRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SuperheroServiceTest {

  @Mock
  private SuperheroRepository repository;

  @InjectMocks
  private SuperheroService service;

  private Superhero hero;
  private Superhero anotherHero;

  @BeforeEach
  void setUp() {
    hero = Superhero.builder().id(1L).name("Superman").build();
    anotherHero = Superhero.builder().id(3L).name("Manolito el fuerte").build();
  }

  @Test
  void shouldReturnMatchingSuperheroesNameIgnoringCaseWhenRepositoryContainsMatches() {
    given(repository.findAll()).willReturn(List.of(
        hero, Superhero.builder().id(2L).name("The Flash").build(), anotherHero)
    );

    Collection<Superhero> heroes = service.searchByName("man");

    assertEquals(List.of(hero, anotherHero), heroes);
  }

  @Test
  void shouldUpdateHeroWhenHeroExists() {
    String heroNameUpdate = "Green Lantern";
    Superhero heroExpected = new Superhero(1L, heroNameUpdate);

    given(repository.findById(1L)).willReturn(Optional.of(hero));
    given(repository.save(any())).willReturn(heroExpected);

    Superhero heroUpdated = service.updateSuperhero(1L, new Superhero(null, heroNameUpdate));

    assertEquals(heroExpected, heroUpdated);
    assertEquals(heroNameUpdate, heroUpdated.getName());
  }
}