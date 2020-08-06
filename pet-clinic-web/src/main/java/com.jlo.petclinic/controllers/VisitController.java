package com.jlo.petclinic.controllers;

import com.jlo.petclinic.model.Pet;
import com.jlo.petclinic.model.Visit;
import com.jlo.petclinic.repositories.PetRepository;
import com.jlo.petclinic.repositories.VisitRepository;
import com.jlo.petclinic.services.PetService;
import com.jlo.petclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public  void setAllowedField(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("petId") long petId, Map<String, Object> model){
        Pet pet = petService.findById(petId);
        model.put("pet",pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    @GetMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String initNewVisitform(@PathVariable("petId") long petId, Map<String, Object >model){
        return  "pets/createOrUpdateVisitForm";
    }
    
    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult result){
        if(result.hasErrors()){
            return "pets/createOrUpdateVisitForm";
        }else {
            visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }



}
