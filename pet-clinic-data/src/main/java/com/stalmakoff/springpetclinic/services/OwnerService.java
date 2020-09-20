package com.stalmakoff.springpetclinic.services;

import com.stalmakoff.springpetclinic.model.Owner;

import java.util.List;


public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);

}
