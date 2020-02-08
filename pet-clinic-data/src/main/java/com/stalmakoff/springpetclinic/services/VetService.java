package com.stalmakoff.springpetclinic.services;

import com.stalmakoff.springpetclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();


}
