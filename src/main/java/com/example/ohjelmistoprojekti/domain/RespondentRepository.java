package com.example.ohjelmistoprojekti.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespondentRepository extends CrudRepository<Respondent, Long> {

}
