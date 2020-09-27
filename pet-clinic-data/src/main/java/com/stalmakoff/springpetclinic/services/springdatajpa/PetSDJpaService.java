package com.stalmakoff.springpetclinic.services.springdatajpa;

import com.stalmakoff.springpetclinic.exceptions.NotFoundException;
import com.stalmakoff.springpetclinic.model.Pet;
import com.stalmakoff.springpetclinic.repositories.PetRepository;
import com.stalmakoff.springpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {

    private final PetRepository petRepository;

    public PetSDJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {

        Set<Pet> pets = new HashSet<>();

        petRepository.findAll().forEach(pets::add);

        return pets;
    }

    @Override
    public Pet findById(Long aLong) {
        Optional<Pet> petOptional = petRepository.findById(aLong);

        if (!petOptional.isPresent()) {
            throw new NotFoundException("Pet Not Found. For ID value: " + aLong.toString() );
        }

        return petOptional.get();
    }

    @Override
    public Pet save(Pet object) {
        System.out.println("Pet Saved, SD Jpa" + object);
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petRepository.deleteById(aLong);
    }
}
