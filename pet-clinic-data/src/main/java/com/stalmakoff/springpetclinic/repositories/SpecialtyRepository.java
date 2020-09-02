package com.stalmakoff.springpetclinic.repositories;

import com.stalmakoff.springpetclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Speciality, Long> {
}
