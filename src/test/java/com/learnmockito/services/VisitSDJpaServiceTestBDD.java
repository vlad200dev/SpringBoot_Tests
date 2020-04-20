package com.learnmockito.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.learntotestviajunit5.model.Visit;
import com.learntotestviajunit5.repositories.VisitRepository;
import com.learntotestviajunit5.services.springdatajpa.VisitSDJpaService;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Копия VisitSDJpaServiceTest класса, но использование BDD
 */
@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTestBDD {

  @Mock
  VisitRepository visitRepository;

  @InjectMocks
  VisitSDJpaService service;

  @DisplayName("Test Find All")
  @Test
  void findAll() {
    //given
    Visit visit = new Visit();
    Set<Visit> visits = new HashSet<>();
    visits.add(visit);
    given(visitRepository.findAll()).willReturn(visits);

    //when
    Set<Visit> foundVisits = service.findAll();

    //then
    then(visitRepository).should().findAll();
    assertThat(foundVisits).hasSize(1);
  }

  @Test
  void findById() {
    //given
    Visit visit = new Visit();
    given(visitRepository.findById(anyLong())).willReturn(Optional.of(visit));

    //when
    Visit foundVisit = service.findById(1L);

    //then
    then(visitRepository).should().findById(anyLong());
    assertThat(foundVisit).isNotNull();
  }

  @Test
  void save() {
    //given
    Visit visit = new Visit();
    given(visitRepository.save(any(Visit.class))).willReturn(visit);

    //when
    Visit savedVisit = service.save(new Visit());

    //then
    then(visitRepository).should().save(any(Visit.class));
    assertThat(savedVisit).isNotNull();
  }

  @Test
  void delete() {
    //given
    Visit visit = new Visit();

    //when
    service.delete(visit);

    //then
    then(visitRepository).should().delete(any(Visit.class));
  }

  @Test
  void deleteById() {
    //when
    service.deleteById(1L);

    //then
    then(visitRepository).should().deleteById(anyLong());
  }
}