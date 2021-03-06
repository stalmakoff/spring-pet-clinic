package com.stalmakoff.springpetclinic.controllers;

import com.stalmakoff.springpetclinic.exceptions.NotFoundException;
import com.stalmakoff.springpetclinic.model.Owner;
import com.stalmakoff.springpetclinic.model.Pet;
import com.stalmakoff.springpetclinic.model.PetType;
import com.stalmakoff.springpetclinic.services.OwnerService;
import com.stalmakoff.springpetclinic.services.PetService;
import com.stalmakoff.springpetclinic.services.PetTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm.html";

    private final PetService petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    public PetController(PetService petService,PetTypeService petTypeService, OwnerService ownerService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes(){
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId){
        return ownerService.findById(ownerId);
    }

    // but could be without @ModelAttribute("owner") as Spring sees that owner correlates with findOwner attribute above and Owner object "owner" that describes what is this object
    // so could be just "public String initCreationForm(Owner owner, Model model)"
    @GetMapping("/pets/new")
    public String initCreationForm(@ModelAttribute("owner")Owner owner, Model model) {
        Pet pet = new Pet();
//        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.getPets().add(pet);
        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            model.put("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            pet.setOwner(owner); // only  after that we could see on owners/{ownerId} all list of pets
            petService.save(pet);
            log.debug("saved pet id:" + pet.getId());

            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid @ModelAttribute Pet pet,@PathVariable("petId") Long id, BindingResult result,@ModelAttribute("owner") Owner owner, Model model) {
        // validate the input data
//        if (StringUtils.hasLength(pet.getName())) {
//            Pet foundPet = owner.getPet(pet.getName());
//            if (foundPet!=null && !foundPet.getId().equals(Long.valueOf(id))) {
//                result.rejectValue("name", "duplicate", "already used for other pet for this owner");
//            }
//        }
//        if (!StringUtils.hasLength(pet.getName())) {
//            result.rejectValue("name", "null", "name of pet cannot be empty");
//        }
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            // update the pet information in database
            // when apply the hibernate db, data store in db in sql style,
            // all the java model only created after apply CrudRepository to retrieve
            // data from database; or created before apply crudRepository to store data to database
            // Therefore, there is no need to update the pet set in owner model.
            // Instead of it, only to maintain the relationship between pet and owner in hibernate db.
            Pet foundPet = petService.findById(id);
            foundPet.setOwner(owner);
            foundPet.setPetType(pet.getPetType());
            foundPet.setBirthDate(pet.getBirthDate());
            foundPet.setName(pet.getName());
//            owner.getPets().add(foundPet);
            petService.save(foundPet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }


}
