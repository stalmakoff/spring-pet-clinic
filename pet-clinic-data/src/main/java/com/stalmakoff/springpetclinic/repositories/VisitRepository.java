package com.stalmakoff.springpetclinic.repositories;

import com.stalmakoff.springpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
