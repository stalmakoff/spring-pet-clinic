package com.stalmakoff.springpetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "special")
public class Speciality extends BaseEntity {

    @Column(name = "description")
    private String description;
}
