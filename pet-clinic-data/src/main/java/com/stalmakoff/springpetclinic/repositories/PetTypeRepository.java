package com.stalmakoff.springpetclinic.repositories;

import com.stalmakoff.springpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
