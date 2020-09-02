package com.stalmakoff.springpetclinic.repositories;

import com.stalmakoff.springpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
