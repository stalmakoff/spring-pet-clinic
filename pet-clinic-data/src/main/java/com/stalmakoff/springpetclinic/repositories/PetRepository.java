package com.stalmakoff.springpetclinic.repositories;

import com.stalmakoff.springpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {



}
