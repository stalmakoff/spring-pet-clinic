package com.stalmakoff.springpetclinic.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="owners")
public class Owner extends Person {

    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String city,
                 String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets;

        log.info("Owner has been created");
        log.debug("in Owner constructor");
    }


    @Column(name="address")
    private String address;
    @Column(name="city")
    private String city;
    @Column(name="telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();
}
