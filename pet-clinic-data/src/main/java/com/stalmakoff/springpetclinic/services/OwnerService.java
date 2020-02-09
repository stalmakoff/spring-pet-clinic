package com.stalmakoff.springpetclinic.services;

import com.stalmakoff.springpetclinic.model.Owner;


public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);



}
