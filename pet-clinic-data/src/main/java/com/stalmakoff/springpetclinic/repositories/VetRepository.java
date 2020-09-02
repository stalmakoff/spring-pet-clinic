package com.stalmakoff.springpetclinic.repositories;

import com.stalmakoff.springpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
